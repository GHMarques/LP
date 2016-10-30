#include "Peca.h"

Peca::Peca(QWidget *parent)
    : QPushButton(parent),
      m_state(Peca::Empty) {
    this->updateIcon();
}

Peca::~Peca() {
}

void Peca::setState(Peca::State state) {
    if (m_state != state) {
        m_state = state;
        this->updateIcon();

        emit stateChanged(state);
    }
}

int Peca::getState(){
    return (int)m_state;
}

void Peca::updateIcon() {
    switch (m_state) {
        case Peca::Empty:
            this->setIcon(QPixmap(":/empty"));
            break;
        case Peca::Filled:
            this->setIcon(QPixmap(":/filled"));
            break;
        case Peca::Selected:
            this->setIcon(QPixmap(":/selected"));
            break;
        case Peca::Jumpable:
            this->setIcon(QPixmap(":/jumpable"));
            break;
    }
}

