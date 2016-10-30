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
    int getState();
    explicit Peca(QWidget *parent = 0);
    ~Peca();

signals:
    void stateChanged(Peca::State state);

public slots:
    void setState(Peca::State state);

private:
    Peca::State m_state;

private slots:
    void updateIcon();

};

#endif // PECA_H
