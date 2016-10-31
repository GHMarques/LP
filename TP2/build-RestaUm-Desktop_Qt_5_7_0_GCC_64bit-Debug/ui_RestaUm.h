/********************************************************************************
** Form generated from reading UI file 'RestaUm.ui'
**
** Created by: Qt User Interface Compiler version 5.7.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_RESTAUM_H
#define UI_RESTAUM_H

#include <Peca.h>
#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_RestaUm
{
public:
    QAction *actionNovo;
    QAction *actionSair;
    QAction *actionTradicional;
    QAction *actionCruz;
    QAction *actionMais;
    QAction *actionBanquinho;
    QAction *actionFlecha;
    QAction *actionPiramide;
    QAction *actionLosango;
    QAction *actionSobre;
    QWidget *centralWidget;
    QGridLayout *gridLayout;
    Peca *peca53;
    Peca *peca54;
    Peca *peca14;
    Peca *peca33;
    Peca *peca31;
    Peca *peca46;
    Peca *peca43;
    Peca *peca52;
    Peca *peca22;
    Peca *peca42;
    Peca *peca41;
    Peca *peca44;
    Peca *peca36;
    Peca *peca24;
    Peca *peca34;
    Peca *peca25;
    Peca *peca23;
    Peca *peca20;
    Peca *peca02;
    Peca *peca35;
    Peca *peca40;
    Peca *peca30;
    Peca *peca03;
    Peca *peca21;
    Peca *peca32;
    Peca *peca13;
    Peca *peca45;
    Peca *peca12;
    Peca *peca04;
    Peca *peca26;
    Peca *peca63;
    Peca *peca62;
    Peca *peca64;
    QLabel *_labelQtd;
    QMenuBar *menuBar;
    QMenu *menuArquivo;
    QMenu *menuModo;
    QMenu *menuAjuda;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *RestaUm)
    {
        if (RestaUm->objectName().isEmpty())
            RestaUm->setObjectName(QStringLiteral("RestaUm"));
        RestaUm->resize(229, 292);
        actionNovo = new QAction(RestaUm);
        actionNovo->setObjectName(QStringLiteral("actionNovo"));
        actionSair = new QAction(RestaUm);
        actionSair->setObjectName(QStringLiteral("actionSair"));
        actionTradicional = new QAction(RestaUm);
        actionTradicional->setObjectName(QStringLiteral("actionTradicional"));
        actionTradicional->setCheckable(true);
        actionTradicional->setChecked(true);
        actionCruz = new QAction(RestaUm);
        actionCruz->setObjectName(QStringLiteral("actionCruz"));
        actionCruz->setCheckable(true);
        actionCruz->setChecked(false);
        actionMais = new QAction(RestaUm);
        actionMais->setObjectName(QStringLiteral("actionMais"));
        actionMais->setCheckable(true);
        actionMais->setChecked(false);
        actionBanquinho = new QAction(RestaUm);
        actionBanquinho->setObjectName(QStringLiteral("actionBanquinho"));
        actionBanquinho->setCheckable(true);
        actionBanquinho->setChecked(false);
        actionFlecha = new QAction(RestaUm);
        actionFlecha->setObjectName(QStringLiteral("actionFlecha"));
        actionFlecha->setCheckable(true);
        actionFlecha->setChecked(false);
        actionPiramide = new QAction(RestaUm);
        actionPiramide->setObjectName(QStringLiteral("actionPiramide"));
        actionPiramide->setCheckable(true);
        actionPiramide->setChecked(false);
        actionLosango = new QAction(RestaUm);
        actionLosango->setObjectName(QStringLiteral("actionLosango"));
        actionLosango->setCheckable(true);
        actionLosango->setChecked(false);
        actionSobre = new QAction(RestaUm);
        actionSobre->setObjectName(QStringLiteral("actionSobre"));
        centralWidget = new QWidget(RestaUm);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        gridLayout = new QGridLayout(centralWidget);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        peca53 = new Peca(centralWidget);
        peca53->setObjectName(QStringLiteral("peca53"));
        peca53->setMinimumSize(QSize(25, 25));
        peca53->setMaximumSize(QSize(25, 25));
        peca53->setIconSize(QSize(25, 25));
        peca53->setFlat(true);

        gridLayout->addWidget(peca53, 5, 3, 1, 1);

        peca54 = new Peca(centralWidget);
        peca54->setObjectName(QStringLiteral("peca54"));
        peca54->setMinimumSize(QSize(25, 25));
        peca54->setMaximumSize(QSize(25, 25));
        peca54->setIconSize(QSize(25, 25));
        peca54->setFlat(true);

        gridLayout->addWidget(peca54, 5, 4, 1, 1);

        peca14 = new Peca(centralWidget);
        peca14->setObjectName(QStringLiteral("peca14"));
        peca14->setMinimumSize(QSize(25, 25));
        peca14->setMaximumSize(QSize(25, 25));
        peca14->setIconSize(QSize(25, 25));
        peca14->setFlat(true);

        gridLayout->addWidget(peca14, 1, 4, 1, 1);

        peca33 = new Peca(centralWidget);
        peca33->setObjectName(QStringLiteral("peca33"));
        peca33->setMinimumSize(QSize(25, 25));
        peca33->setMaximumSize(QSize(25, 25));
        peca33->setIconSize(QSize(25, 25));
        peca33->setFlat(true);

        gridLayout->addWidget(peca33, 3, 3, 1, 1);

        peca31 = new Peca(centralWidget);
        peca31->setObjectName(QStringLiteral("peca31"));
        peca31->setMinimumSize(QSize(25, 25));
        peca31->setMaximumSize(QSize(25, 25));
        peca31->setIconSize(QSize(25, 25));
        peca31->setFlat(true);

        gridLayout->addWidget(peca31, 3, 1, 1, 1);

        peca46 = new Peca(centralWidget);
        peca46->setObjectName(QStringLiteral("peca46"));
        peca46->setMinimumSize(QSize(25, 25));
        peca46->setMaximumSize(QSize(25, 25));
        peca46->setIconSize(QSize(25, 25));
        peca46->setFlat(true);

        gridLayout->addWidget(peca46, 4, 6, 1, 1);

        peca43 = new Peca(centralWidget);
        peca43->setObjectName(QStringLiteral("peca43"));
        peca43->setMinimumSize(QSize(25, 25));
        peca43->setMaximumSize(QSize(25, 25));
        peca43->setIconSize(QSize(25, 25));
        peca43->setFlat(true);

        gridLayout->addWidget(peca43, 4, 3, 1, 1);

        peca52 = new Peca(centralWidget);
        peca52->setObjectName(QStringLiteral("peca52"));
        peca52->setMinimumSize(QSize(25, 25));
        peca52->setMaximumSize(QSize(25, 25));
        peca52->setIconSize(QSize(25, 25));
        peca52->setFlat(true);

        gridLayout->addWidget(peca52, 5, 2, 1, 1);

        peca22 = new Peca(centralWidget);
        peca22->setObjectName(QStringLiteral("peca22"));
        peca22->setMinimumSize(QSize(25, 25));
        peca22->setMaximumSize(QSize(25, 25));
        peca22->setIconSize(QSize(25, 25));
        peca22->setFlat(true);

        gridLayout->addWidget(peca22, 2, 2, 1, 1);

        peca42 = new Peca(centralWidget);
        peca42->setObjectName(QStringLiteral("peca42"));
        peca42->setMinimumSize(QSize(25, 25));
        peca42->setMaximumSize(QSize(25, 25));
        peca42->setIconSize(QSize(25, 25));
        peca42->setFlat(true);

        gridLayout->addWidget(peca42, 4, 2, 1, 1);

        peca41 = new Peca(centralWidget);
        peca41->setObjectName(QStringLiteral("peca41"));
        peca41->setMinimumSize(QSize(25, 25));
        peca41->setMaximumSize(QSize(25, 25));
        peca41->setIconSize(QSize(25, 25));
        peca41->setFlat(true);

        gridLayout->addWidget(peca41, 4, 1, 1, 1);

        peca44 = new Peca(centralWidget);
        peca44->setObjectName(QStringLiteral("peca44"));
        peca44->setMinimumSize(QSize(25, 25));
        peca44->setMaximumSize(QSize(25, 25));
        peca44->setIconSize(QSize(25, 25));
        peca44->setFlat(true);

        gridLayout->addWidget(peca44, 4, 4, 1, 1);

        peca36 = new Peca(centralWidget);
        peca36->setObjectName(QStringLiteral("peca36"));
        peca36->setMinimumSize(QSize(25, 25));
        peca36->setMaximumSize(QSize(25, 25));
        peca36->setIconSize(QSize(25, 25));
        peca36->setFlat(true);

        gridLayout->addWidget(peca36, 3, 6, 1, 1);

        peca24 = new Peca(centralWidget);
        peca24->setObjectName(QStringLiteral("peca24"));
        peca24->setMinimumSize(QSize(25, 25));
        peca24->setMaximumSize(QSize(25, 25));
        peca24->setIconSize(QSize(25, 25));
        peca24->setFlat(true);

        gridLayout->addWidget(peca24, 2, 4, 1, 1);

        peca34 = new Peca(centralWidget);
        peca34->setObjectName(QStringLiteral("peca34"));
        peca34->setMinimumSize(QSize(25, 25));
        peca34->setMaximumSize(QSize(25, 25));
        peca34->setIconSize(QSize(25, 25));
        peca34->setFlat(true);

        gridLayout->addWidget(peca34, 3, 4, 1, 1);

        peca25 = new Peca(centralWidget);
        peca25->setObjectName(QStringLiteral("peca25"));
        peca25->setMinimumSize(QSize(25, 25));
        peca25->setMaximumSize(QSize(25, 25));
        peca25->setIconSize(QSize(25, 25));
        peca25->setFlat(true);

        gridLayout->addWidget(peca25, 2, 5, 1, 1);

        peca23 = new Peca(centralWidget);
        peca23->setObjectName(QStringLiteral("peca23"));
        peca23->setMinimumSize(QSize(25, 25));
        peca23->setMaximumSize(QSize(25, 25));
        peca23->setIconSize(QSize(25, 25));
        peca23->setFlat(true);

        gridLayout->addWidget(peca23, 2, 3, 1, 1);

        peca20 = new Peca(centralWidget);
        peca20->setObjectName(QStringLiteral("peca20"));
        peca20->setMinimumSize(QSize(25, 25));
        peca20->setMaximumSize(QSize(25, 25));
        peca20->setIconSize(QSize(25, 25));
        peca20->setFlat(true);

        gridLayout->addWidget(peca20, 2, 0, 1, 1);

        peca02 = new Peca(centralWidget);
        peca02->setObjectName(QStringLiteral("peca02"));
        peca02->setMinimumSize(QSize(25, 25));
        peca02->setMaximumSize(QSize(25, 25));
        peca02->setIconSize(QSize(25, 25));
        peca02->setFlat(true);

        gridLayout->addWidget(peca02, 0, 2, 1, 1);

        peca35 = new Peca(centralWidget);
        peca35->setObjectName(QStringLiteral("peca35"));
        peca35->setMinimumSize(QSize(25, 25));
        peca35->setMaximumSize(QSize(25, 25));
        peca35->setIconSize(QSize(25, 25));
        peca35->setFlat(true);

        gridLayout->addWidget(peca35, 3, 5, 1, 1);

        peca40 = new Peca(centralWidget);
        peca40->setObjectName(QStringLiteral("peca40"));
        peca40->setMinimumSize(QSize(25, 25));
        peca40->setMaximumSize(QSize(25, 25));
        peca40->setIconSize(QSize(25, 25));
        peca40->setFlat(true);

        gridLayout->addWidget(peca40, 4, 0, 1, 1);

        peca30 = new Peca(centralWidget);
        peca30->setObjectName(QStringLiteral("peca30"));
        peca30->setMinimumSize(QSize(25, 25));
        peca30->setMaximumSize(QSize(25, 25));
        peca30->setIconSize(QSize(25, 25));
        peca30->setFlat(true);

        gridLayout->addWidget(peca30, 3, 0, 1, 1);

        peca03 = new Peca(centralWidget);
        peca03->setObjectName(QStringLiteral("peca03"));
        peca03->setMinimumSize(QSize(25, 25));
        peca03->setMaximumSize(QSize(25, 25));
        peca03->setIconSize(QSize(25, 25));
        peca03->setFlat(true);

        gridLayout->addWidget(peca03, 0, 3, 1, 1);

        peca21 = new Peca(centralWidget);
        peca21->setObjectName(QStringLiteral("peca21"));
        peca21->setMinimumSize(QSize(25, 25));
        peca21->setMaximumSize(QSize(25, 25));
        peca21->setIconSize(QSize(25, 25));
        peca21->setFlat(true);

        gridLayout->addWidget(peca21, 2, 1, 1, 1);

        peca32 = new Peca(centralWidget);
        peca32->setObjectName(QStringLiteral("peca32"));
        peca32->setMinimumSize(QSize(25, 25));
        peca32->setMaximumSize(QSize(25, 25));
        peca32->setIconSize(QSize(25, 25));
        peca32->setFlat(true);

        gridLayout->addWidget(peca32, 3, 2, 1, 1);

        peca13 = new Peca(centralWidget);
        peca13->setObjectName(QStringLiteral("peca13"));
        peca13->setMinimumSize(QSize(25, 25));
        peca13->setMaximumSize(QSize(25, 25));
        peca13->setIconSize(QSize(25, 25));
        peca13->setFlat(true);

        gridLayout->addWidget(peca13, 1, 3, 1, 1);

        peca45 = new Peca(centralWidget);
        peca45->setObjectName(QStringLiteral("peca45"));
        peca45->setMinimumSize(QSize(25, 25));
        peca45->setMaximumSize(QSize(25, 25));
        peca45->setIconSize(QSize(25, 25));
        peca45->setFlat(true);

        gridLayout->addWidget(peca45, 4, 5, 1, 1);

        peca12 = new Peca(centralWidget);
        peca12->setObjectName(QStringLiteral("peca12"));
        peca12->setMinimumSize(QSize(25, 25));
        peca12->setMaximumSize(QSize(25, 25));
        peca12->setIconSize(QSize(25, 25));
        peca12->setFlat(true);

        gridLayout->addWidget(peca12, 1, 2, 1, 1);

        peca04 = new Peca(centralWidget);
        peca04->setObjectName(QStringLiteral("peca04"));
        peca04->setMinimumSize(QSize(25, 25));
        peca04->setMaximumSize(QSize(25, 25));
        peca04->setIconSize(QSize(25, 25));
        peca04->setFlat(true);

        gridLayout->addWidget(peca04, 0, 4, 1, 1);

        peca26 = new Peca(centralWidget);
        peca26->setObjectName(QStringLiteral("peca26"));
        peca26->setMinimumSize(QSize(25, 25));
        peca26->setMaximumSize(QSize(25, 25));
        peca26->setIconSize(QSize(25, 25));
        peca26->setFlat(true);

        gridLayout->addWidget(peca26, 2, 6, 1, 1);

        peca63 = new Peca(centralWidget);
        peca63->setObjectName(QStringLiteral("peca63"));
        peca63->setMinimumSize(QSize(25, 25));
        peca63->setMaximumSize(QSize(25, 25));
        peca63->setIconSize(QSize(25, 25));
        peca63->setFlat(true);

        gridLayout->addWidget(peca63, 6, 3, 1, 1);

        peca62 = new Peca(centralWidget);
        peca62->setObjectName(QStringLiteral("peca62"));
        peca62->setMinimumSize(QSize(25, 25));
        peca62->setMaximumSize(QSize(25, 25));
        peca62->setIconSize(QSize(25, 25));
        peca62->setFlat(true);

        gridLayout->addWidget(peca62, 6, 2, 1, 1);

        peca64 = new Peca(centralWidget);
        peca64->setObjectName(QStringLiteral("peca64"));
        peca64->setMinimumSize(QSize(25, 25));
        peca64->setMaximumSize(QSize(25, 25));
        peca64->setIconSize(QSize(25, 25));
        peca64->setFlat(true);

        gridLayout->addWidget(peca64, 6, 4, 1, 1);

        _labelQtd = new QLabel(centralWidget);
        _labelQtd->setObjectName(QStringLiteral("_labelQtd"));

        gridLayout->addWidget(_labelQtd, 7, 1, 1, 5, Qt::AlignBottom);

        RestaUm->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(RestaUm);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 229, 21));
        menuArquivo = new QMenu(menuBar);
        menuArquivo->setObjectName(QStringLiteral("menuArquivo"));
        menuModo = new QMenu(menuBar);
        menuModo->setObjectName(QStringLiteral("menuModo"));
        menuAjuda = new QMenu(menuBar);
        menuAjuda->setObjectName(QStringLiteral("menuAjuda"));
        RestaUm->setMenuBar(menuBar);
        statusBar = new QStatusBar(RestaUm);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        RestaUm->setStatusBar(statusBar);

        menuBar->addAction(menuArquivo->menuAction());
        menuBar->addAction(menuModo->menuAction());
        menuBar->addAction(menuAjuda->menuAction());
        menuArquivo->addAction(actionNovo);
        menuArquivo->addAction(actionSair);
        menuModo->addAction(actionTradicional);
        menuModo->addAction(actionCruz);
        menuModo->addAction(actionMais);
        menuModo->addAction(actionBanquinho);
        menuModo->addAction(actionFlecha);
        menuModo->addAction(actionPiramide);
        menuModo->addAction(actionLosango);
        menuAjuda->addAction(actionSobre);

        retranslateUi(RestaUm);

        QMetaObject::connectSlotsByName(RestaUm);
    } // setupUi

    void retranslateUi(QMainWindow *RestaUm)
    {
        RestaUm->setWindowTitle(QApplication::translate("RestaUm", "RestaUm", 0));
        actionNovo->setText(QApplication::translate("RestaUm", "Novo", 0));
        actionSair->setText(QApplication::translate("RestaUm", "Sair", 0));
        actionTradicional->setText(QApplication::translate("RestaUm", "Tradicional", 0));
        actionCruz->setText(QApplication::translate("RestaUm", "Cruz", 0));
        actionMais->setText(QApplication::translate("RestaUm", "Mais", 0));
        actionBanquinho->setText(QApplication::translate("RestaUm", "Banquinho", 0));
        actionFlecha->setText(QApplication::translate("RestaUm", "Flecha", 0));
        actionPiramide->setText(QApplication::translate("RestaUm", "Piramide", 0));
        actionLosango->setText(QApplication::translate("RestaUm", "Losango", 0));
        actionSobre->setText(QApplication::translate("RestaUm", "Sobre", 0));
        _labelQtd->setText(QApplication::translate("RestaUm", "Pe\303\247as remanecestes:", 0));
        menuArquivo->setTitle(QApplication::translate("RestaUm", "Arquivo", 0));
        menuModo->setTitle(QApplication::translate("RestaUm", "Modo", 0));
        menuAjuda->setTitle(QApplication::translate("RestaUm", "Ajuda", 0));
    } // retranslateUi

};

namespace Ui {
    class RestaUm: public Ui_RestaUm {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_RESTAUM_H
