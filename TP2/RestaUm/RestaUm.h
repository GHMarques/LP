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
    explicit RestaUm(QWidget *parent = 0);
    ~RestaUm();
    void Tradicional();
    void Mais();
    void Cruz();
    void Piramide();
    void Losango();
    void Banquinho();
    void Flecha();
signals:
    void gameOver();

private:
    Ui::RestaUm *ui;
    Peca* m_pecas[7][7];
    int qtd_pecas;
private slots:
    void play();
    void mostrarSobre();
    void mostrarFimJogo();
    void trocarModo(QAction* modo);

};

#endif // RESTAUM_H
