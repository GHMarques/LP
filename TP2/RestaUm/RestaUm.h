#ifndef RESTAUM_H
#define RESTAUM_H

#include <Peca.h>
#include <QMainWindow>

namespace Ui {
    class RestaUm;
}

class RestaUm : public QMainWindow {
    Q_OBJECT

public:
    //explicit RestaUm(QWidget *parent = 0);
    RestaUm(QWidget *parent = 0);
    ~RestaUm();
    void DrawMap();
signals:
    void gameOver();
    void vitoria();
    //void qtd_pecasChanged();
private:
    Ui::RestaUm *ui;
    Peca* m_pecas[7][7];
    int qtd_pecas;
private slots:
    void Tradicional();
    void Mais();
    void Cruz();
    void Piramide();
    void Losango();
    void Banquinho();
    void Flecha();
    void play();
    void NewGame();
    void mostrarSobre();
    void mostrarFimJogo();
    void trocarModo(QAction* modo);
    //void updateQtd_pecasLabel();
};

#endif // RESTAUM_H
