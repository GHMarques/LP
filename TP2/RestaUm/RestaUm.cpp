#include "RestaUm.h"
#include "ui_RestaUm.h"

#include <QDebug>
#include <QActionGroup>
#include <QMessageBox>
RestaUm::RestaUm(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::RestaUm) {

    ui->setupUi(this);

    QActionGroup* group = new QActionGroup(this);
    group->setExclusive(true);
    group->addAction(ui->actionTradicional);
    group->addAction(ui->actionCruz);
    group->addAction(ui->actionMais);
    group->addAction(ui->actionBanquinho);
    group->addAction(ui->actionFlecha);
    group->addAction(ui->actionPiramide);
    group->addAction(ui->actionLosango);

    for (int r = 0; r < 7; r++) {
        for (int c = 0; c < 7; c++) {
            m_pecas[r][c] =
              this->findChild<Peca*>(
                QString("peca%1%2").arg(r).arg(c));
            if (m_pecas[r][c]) {
                QObject::connect(
                  m_pecas[r][c],
                  SIGNAL(clicked()),
                  this,
                  SLOT(play()));

                //m_pecas[r][c]->setState(Peca::Filled);
            }
        }
    }


    //inicializacao
    startGame();

    QObject::connect(
        group,
        SIGNAL(triggered(QAction*)),
        this,
        SLOT(trocarModo(QAction*)));

    QObject::connect(
        ui->actionSair,
        SIGNAL(triggered()),
        qApp,
        SLOT(quit()));

    QObject::connect(
        ui->actionSobre,
        SIGNAL(triggered()),
        this,
        SLOT(mostrarSobre()));

    QObject::connect(
        ui->actionNovo,
        SIGNAL(triggered()),
        this,
        SLOT(NewGame()));

    QObject::connect(
        this,
        SIGNAL(vitoria()),
        this,
        SLOT(mostrarFimJogo()));

    QObject::connect(
        this,
        SIGNAL(gameOver()),
        this,
        SLOT(mostrarFimJogo()));

    this->adjustSize();
    this->setFixedSize(this->size());
}

RestaUm::~RestaUm() {
    delete ui;
}

void RestaUm::startGame(){
    DrawMap();
    this->estado = RestaUm::Selecionar;
    atualizarCoordenadas();
}

void RestaUm::DrawMap(){
    //Tabuleiros
    if (ui->actionTradicional->isChecked())
        Tradicional();
    else if (ui->actionCruz->isChecked())
        Cruz();
    else if (ui->actionMais->isChecked())
        Mais();
    else if (ui->actionBanquinho->isChecked())
        Banquinho();
    else if (ui->actionFlecha->isChecked())
        Flecha();
    else if (ui->actionPiramide->isChecked())
        Piramide();
    else if (ui->actionLosango->isChecked())
        Losango();
    atualizarLabelQtdPecas();
    this->estado = RestaUm::Selecionar;
}

//_SLOTS_

void RestaUm::play() {
    Peca* peca = qobject_cast<Peca*>(
                QObject::sender());
    if(this->estado == RestaUm::Selecionar){
        peca->setState(Peca::Selected);
        this->estado = RestaUm::Escolher;
        exibirJogadas(peca);
    }else{
        if(peca->getState() == Peca::Selected){
            peca->setState(Peca::Filled);
            this->estado = RestaUm::Selecionar;
            removerSelecionados();
        }
        else if(peca->getState() == Peca::Jumpable){
            this->estado = RestaUm::Selecionar;
            int i = 0, j = 0, flag = 0;
            for(i=0;i<=6 && !flag;i++){
                for(j=0;j<=6 && !flag;j++){
                    if(m_pecas[i][j]){
                        if(m_pecas[i][j]->getState() == Peca::Selected)
                            flag = 1;
                    }

                }
            }
            i--;
            j--;
            //verifica se esta na mesma linha ou coluna
            if(peca->getX() == i){
                //verifica se esta a esquerda ou direita
                if(peca->getY() < j)
                    m_pecas[i][j-1]->setState(Peca::Empty);
                else
                    m_pecas[i][j+1]->setState(Peca::Empty);
            } else{
                //verifica se esta em cima ou abaixo
                if(peca->getX() < i)
                    m_pecas[i-1][j]->setState(Peca::Empty);
                else
                    m_pecas[i+1][j]->setState(Peca::Empty);
            }
            qtd_pecas--;
            atualizarLabelQtdPecas();
            peca->setState(Peca::Filled);
            m_pecas[i][j]->setState(Peca::Empty);
            removerSelecionados();
        }
        else{
            QMessageBox::information(this,
                tr("Ops"),
                tr("Desmarque a peça selecionada ou selecione as peças marcadas"));
        }
    }
    verifica_vitoria();
}
bool RestaUm::verificaQtdJogadas(){
    int i = 0, j = 0;
    bool verifica = false;
    for(i=0;i<=6 && !verifica;i++){
        for(j=0;j<=6 && !verifica;j++){
            if(m_pecas[i][j]){
                if(m_pecas[i][j]->getState() == Peca::Filled || m_pecas[i][j]->getState() == Peca::Selected){
                    //Cima
                    if((i - 2) >= 0 && m_pecas[i-2][j]){
                        if((m_pecas[i-2][j]->getState() == Peca::Empty || m_pecas[i-2][j]->getState() == Peca::Jumpable) && m_pecas[i-1][j]->getState() == Peca::Filled)
                            verifica = true;
                    }
                    //Baixo
                    if((i + 2) <= 6 && m_pecas[i+2][j]){
                        if((m_pecas[i+2][j]->getState() == Peca::Empty || m_pecas[i+2][j]->getState() == Peca::Jumpable) && m_pecas[i+1][j]->getState() == Peca::Filled)
                            verifica = true;
                    }
                    //Esquerda
                    if((j - 2) >= 0 && m_pecas[i][j-2]){
                        if((m_pecas[i][j-2]->getState() == Peca::Empty || m_pecas[i][j-2]->getState() == Peca::Jumpable) && m_pecas[i][j-1]->getState() == Peca::Filled)
                            verifica = true;
                    }
                    //Direita
                    if((j + 2) <= 6 && m_pecas[i][j+2]){
                        if((m_pecas[i][j+2]->getState() == Peca::Empty || m_pecas[i][j+2]->getState() == Peca::Jumpable) && m_pecas[i][j+1]->getState() == Peca::Filled)
                            verifica = true;
                    }
                }

            }

        }
    }
    return verifica;
}

void RestaUm::verifica_vitoria(){
    if(qtd_pecas == 1)
        emit vitoria();
    else{
        if(!verificaQtdJogadas())
            emit gameOver();
    }
}

void RestaUm::atualizarLabelQtdPecas(){
    ui->_labelQtd->setText("Peças Remanecentes: " + QString::number(qtd_pecas));
}

void RestaUm::exibirJogadas(Peca* peca){
    int i = 0, j = 0, cont = 0, posicao = -1;
    i = peca->getX();
    j = peca->getY();
    if(peca->getState() == Peca::Selected){
        //Cima
        if((i - 2) >= 0 && m_pecas[i-2][j]){
            if(m_pecas[i-2][j]->getState() == Peca::Empty && m_pecas[i-1][j]->getState() == Peca::Filled){
                m_pecas[i-2][j]->setState(Peca::Jumpable);
                cont++;
                posicao = 0;
            }
        }
        //Baixo
        if((i + 2) <= 6 && m_pecas[i+2][j]){
            if(m_pecas[i+2][j]->getState() == Peca::Empty && m_pecas[i+1][j]->getState() == Peca::Filled){
                m_pecas[i+2][j]->setState(Peca::Jumpable);
                cont++;
                posicao = 1;
            }
        }
        //Esquerda
        if((j - 2) >= 0 && m_pecas[i][j-2]){
            if(m_pecas[i][j-2]->getState() == Peca::Empty && m_pecas[i][j-1]->getState() == Peca::Filled){
                m_pecas[i][j-2]->setState(Peca::Jumpable);
                cont++;
                posicao = 2;
            }
        }
        //Direita
        if((j + 2) <= 6 && m_pecas[i][j+2]){
            if(m_pecas[i][j+2]->getState() == Peca::Empty && m_pecas[i][j+1]->getState() == Peca::Filled){
                m_pecas[i][j+2]->setState(Peca::Jumpable);
                cont++;
                posicao = 3;
            }
        }
        //uma jogada possivel
        if(cont == 1){
            switch(posicao){
                case 0:
                    m_pecas[i-1][j]->setState(Peca::Empty);
                    m_pecas[i-2][j]->setState(Peca::Filled);
                    break;
                case 1:
                    m_pecas[i+1][j]->setState(Peca::Empty);
                    m_pecas[i+2][j]->setState(Peca::Filled);
                    break;
                case 2:
                    m_pecas[i][j-1]->setState(Peca::Empty);
                    m_pecas[i][j-2]->setState(Peca::Filled);
                    break;
                case 3:
                    m_pecas[i][j+1]->setState(Peca::Empty);
                    m_pecas[i][j+2]->setState(Peca::Filled);
                    break;
            }
            this->estado = RestaUm::Selecionar;
            peca->setState(Peca::Empty);
            //peca->setState(Peca::Filled);
            this->qtd_pecas--;
            atualizarLabelQtdPecas();
        }
    }

    //peca->setState(Peca::Filled);
}

void RestaUm::NewGame() {
    DrawMap();
    this->estado = RestaUm::Selecionar;
}

void RestaUm::mostrarSobre() {
    QMessageBox::information(this,
       tr("Sobre"),
       tr("Resta Um\n\nGustavo Marques - gustavo.marques2011@gmail.com\nWendell Ferreira - wendellf.sa@gmail.com"));
}

void RestaUm::mostrarFimJogo() {
    if(qtd_pecas==1){
        QMessageBox::information(this,
            tr("Fim"),
            tr("Parabéns, você venceu!"));
    }else{
        QMessageBox::information(this,
            tr("Fim"),
            tr("Mals, tente no semestre que vem!"));
    }
}

void RestaUm::trocarModo(QAction* modo) {
    if (modo == ui->actionTradicional)
        qDebug() << "modo: tradicional";
    else if (modo == ui->actionCruz)
        qDebug() << "modo: cruz";
    else if (modo == ui->actionMais)
        qDebug() << "modo: mais";
    else if (modo == ui->actionBanquinho)
        qDebug() << "modo: banquinho";
    else if (modo == ui->actionFlecha)
        qDebug() << "modo: flecha";
    else if (modo == ui->actionPiramide)
        qDebug() << "modo: piramide";
    else if (modo == ui->actionLosango)
        qDebug() << "modo: losango";
    this->estado = RestaUm::Selecionar;
    DrawMap();
}

void RestaUm::Tradicional(){
    for(int i=0;i<=6;i++)
        for(int j=0;j<=6;j++)
            if(m_pecas[i][j])
                m_pecas[i][j]->setState(Peca::Filled);
    m_pecas[3][3]->setState(Peca::Empty);
    qtd_pecas = 32;
}

void RestaUm::Banquinho(){
    for(int i=0;i<=6;i++)
        for(int j=0;j<=6;j++)
            if(m_pecas[i][j])
                m_pecas[i][j]->setState(Peca::Empty);

    for(int i=0;i<4;i++){
        m_pecas[i][2]->setState(Peca::Filled);
        m_pecas[i][4]->setState(Peca::Filled);
    }

    for(int i=0;i<3;i++)
        m_pecas[i][3]->setState(Peca::Filled);
    qtd_pecas = 11;
}

void RestaUm::Mais(){
    for(int i=0;i<=6;i++)
        for(int j=0;j<=6;j++)
            if(m_pecas[i][j])
                m_pecas[i][j]->setState(Peca::Empty);

    for(int i=1;i<6;i++){
        m_pecas[3][i]->setState(Peca::Filled);
        m_pecas[i][3]->setState(Peca::Filled);
    }
    qtd_pecas = 9;
}

void RestaUm::Cruz(){
    for(int i=0;i<=6;i++)
        for(int j=0;j<=6;j++)
            if(m_pecas[i][j])
                m_pecas[i][j]->setState(Peca::Empty);

    for(int i=1;i<=4;i++){
        m_pecas[i][3]->setState(Peca::Filled);
    }

    m_pecas[2][2]->setState(Peca::Filled);
    m_pecas[2][3]->setState(Peca::Filled);
    m_pecas[2][4]->setState(Peca::Filled);
    qtd_pecas = 6;
}

void RestaUm::Flecha(){
    for(int i=0;i<=6;i++)
        for(int j=0;j<=6;j++)
            if(m_pecas[i][j])
                m_pecas[i][j]->setState(Peca::Empty);

    for(int i=0;i<=6;i++){
        m_pecas[i][3]->setState(Peca::Filled);
    }


    for(int i=2;i<=4;i++){
        m_pecas[1][i]->setState(Peca::Filled);
        m_pecas[5][i]->setState(Peca::Filled);
        m_pecas[6][i]->setState(Peca::Filled);
    }
    for(int i=1;i<6;i++){
        m_pecas[2][i]->setState(Peca::Filled);
    }
    m_pecas[0][3]->setState(Peca::Filled);
    qtd_pecas = 17;
}

void RestaUm::Losango(){
    qtd_pecas = 24;
    for(int i=0;i<=6;i++)
        for(int j=0;j<=6;j++)
            if(m_pecas[i][j])
                m_pecas[i][j]->setState(Peca::Empty);

    m_pecas[0][3]->setState(Peca::Filled);
    m_pecas[6][3]->setState(Peca::Filled);
    for(int i=2;i<=4;i++){
        m_pecas[1][i]->setState(Peca::Filled);
        m_pecas[5][i]->setState(Peca::Filled);
    }
    for(int i=1;i<=5;i++){
        m_pecas[2][i]->setState(Peca::Filled);
        m_pecas[4][i]->setState(Peca::Filled);
    }
    for(int i=0;i<=6;i++){
        m_pecas[3][i]->setState(Peca::Filled);
    }
    m_pecas[3][3]->setState(Peca::Empty);


}

void RestaUm::Piramide(){
    qtd_pecas = 16;
    for(int i=0;i<=6;i++)
        for(int j=0;j<=6;j++)
            if(m_pecas[i][j])
                m_pecas[i][j]->setState(Peca::Empty);

     for(int i=2;i<5;i++)  m_pecas[2][i]->setState(Peca::Filled);

     for(int i=1;i<6;i++)  m_pecas[3][i]->setState(Peca::Filled);

     for(int i=0;i<=6;i++)  m_pecas[4][i]->setState(Peca::Filled);

     m_pecas[1][3]->setState(Peca::Filled);
}

void RestaUm::atualizarCoordenadas(){
    int i = 0, j = 0;
    for(i=0;i<=6;i++){
        for(j=0;j<=6;j++){
            if(m_pecas[i][j]){
                m_pecas[i][j]->setX(i);
                m_pecas[i][j]->setY(j);
            }
        }
    }
}

void RestaUm::removerSelecionados(){
    int i = 0, j = 0;
    for(i=0;i<=6;i++){
        for(j=0;j<=6;j++){
            if(m_pecas[i][j]){
                if(m_pecas[i][j]->getState() == Peca::Jumpable)
                    m_pecas[i][j]->setState(Peca::Empty);
            }

        }
    }
}
