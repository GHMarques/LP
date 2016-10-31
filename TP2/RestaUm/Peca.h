#ifndef PECA_H
#define PECA_H

#include <QPushButton>

class Peca : public QPushButton {
    Q_OBJECT

public:
    enum State {
        Empty,
        Filled,
        Selected,
        Jumpable
    };
    explicit Peca(QWidget *parent = 0);
    ~Peca();

signals:
    void stateChanged(Peca::State state);

public slots:
    void setState(Peca::State state);
    Peca::State getState();
    void setX(int i);
    int getX();
    void setY(int j);
    int getY();

private:
    Peca::State m_state;
    int x;
    int y;

private slots:
    void updateIcon();

};

#endif // PECA_H
