/****************************************************************************
** Meta object code from reading C++ file 'RestaUm.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.7.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../RestaUm/RestaUm.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'RestaUm.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.7.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_RestaUm_t {
    QByteArrayData data[24];
    char stringdata0[237];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_RestaUm_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_RestaUm_t qt_meta_stringdata_RestaUm = {
    {
QT_MOC_LITERAL(0, 0, 7), // "RestaUm"
QT_MOC_LITERAL(1, 8, 8), // "gameOver"
QT_MOC_LITERAL(2, 17, 0), // ""
QT_MOC_LITERAL(3, 18, 7), // "vitoria"
QT_MOC_LITERAL(4, 26, 11), // "Tradicional"
QT_MOC_LITERAL(5, 38, 4), // "Mais"
QT_MOC_LITERAL(6, 43, 4), // "Cruz"
QT_MOC_LITERAL(7, 48, 8), // "Piramide"
QT_MOC_LITERAL(8, 57, 7), // "Losango"
QT_MOC_LITERAL(9, 65, 9), // "Banquinho"
QT_MOC_LITERAL(10, 75, 6), // "Flecha"
QT_MOC_LITERAL(11, 82, 4), // "play"
QT_MOC_LITERAL(12, 87, 7), // "NewGame"
QT_MOC_LITERAL(13, 95, 12), // "mostrarSobre"
QT_MOC_LITERAL(14, 108, 14), // "mostrarFimJogo"
QT_MOC_LITERAL(15, 123, 10), // "trocarModo"
QT_MOC_LITERAL(16, 134, 8), // "QAction*"
QT_MOC_LITERAL(17, 143, 4), // "modo"
QT_MOC_LITERAL(18, 148, 13), // "exibirJogadas"
QT_MOC_LITERAL(19, 162, 5), // "Peca*"
QT_MOC_LITERAL(20, 168, 4), // "peca"
QT_MOC_LITERAL(21, 173, 20), // "atualizarCoordenadas"
QT_MOC_LITERAL(22, 194, 19), // "removerSelecionados"
QT_MOC_LITERAL(23, 214, 22) // "atualizarLabelQtdPecas"

    },
    "RestaUm\0gameOver\0\0vitoria\0Tradicional\0"
    "Mais\0Cruz\0Piramide\0Losango\0Banquinho\0"
    "Flecha\0play\0NewGame\0mostrarSobre\0"
    "mostrarFimJogo\0trocarModo\0QAction*\0"
    "modo\0exibirJogadas\0Peca*\0peca\0"
    "atualizarCoordenadas\0removerSelecionados\0"
    "atualizarLabelQtdPecas"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_RestaUm[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
      18,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       2,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    0,  104,    2, 0x06 /* Public */,
       3,    0,  105,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       4,    0,  106,    2, 0x08 /* Private */,
       5,    0,  107,    2, 0x08 /* Private */,
       6,    0,  108,    2, 0x08 /* Private */,
       7,    0,  109,    2, 0x08 /* Private */,
       8,    0,  110,    2, 0x08 /* Private */,
       9,    0,  111,    2, 0x08 /* Private */,
      10,    0,  112,    2, 0x08 /* Private */,
      11,    0,  113,    2, 0x08 /* Private */,
      12,    0,  114,    2, 0x08 /* Private */,
      13,    0,  115,    2, 0x08 /* Private */,
      14,    0,  116,    2, 0x08 /* Private */,
      15,    1,  117,    2, 0x08 /* Private */,
      18,    1,  120,    2, 0x08 /* Private */,
      21,    0,  123,    2, 0x08 /* Private */,
      22,    0,  124,    2, 0x08 /* Private */,
      23,    0,  125,    2, 0x08 /* Private */,

 // signals: parameters
    QMetaType::Void,
    QMetaType::Void,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, 0x80000000 | 16,   17,
    QMetaType::Void, 0x80000000 | 19,   20,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void RestaUm::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        RestaUm *_t = static_cast<RestaUm *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->gameOver(); break;
        case 1: _t->vitoria(); break;
        case 2: _t->Tradicional(); break;
        case 3: _t->Mais(); break;
        case 4: _t->Cruz(); break;
        case 5: _t->Piramide(); break;
        case 6: _t->Losango(); break;
        case 7: _t->Banquinho(); break;
        case 8: _t->Flecha(); break;
        case 9: _t->play(); break;
        case 10: _t->NewGame(); break;
        case 11: _t->mostrarSobre(); break;
        case 12: _t->mostrarFimJogo(); break;
        case 13: _t->trocarModo((*reinterpret_cast< QAction*(*)>(_a[1]))); break;
        case 14: _t->exibirJogadas((*reinterpret_cast< Peca*(*)>(_a[1]))); break;
        case 15: _t->atualizarCoordenadas(); break;
        case 16: _t->removerSelecionados(); break;
        case 17: _t->atualizarLabelQtdPecas(); break;
        default: ;
        }
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        switch (_id) {
        default: *reinterpret_cast<int*>(_a[0]) = -1; break;
        case 14:
            switch (*reinterpret_cast<int*>(_a[1])) {
            default: *reinterpret_cast<int*>(_a[0]) = -1; break;
            case 0:
                *reinterpret_cast<int*>(_a[0]) = qRegisterMetaType< Peca* >(); break;
            }
            break;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        void **func = reinterpret_cast<void **>(_a[1]);
        {
            typedef void (RestaUm::*_t)();
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&RestaUm::gameOver)) {
                *result = 0;
                return;
            }
        }
        {
            typedef void (RestaUm::*_t)();
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&RestaUm::vitoria)) {
                *result = 1;
                return;
            }
        }
    }
}

const QMetaObject RestaUm::staticMetaObject = {
    { &QMainWindow::staticMetaObject, qt_meta_stringdata_RestaUm.data,
      qt_meta_data_RestaUm,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *RestaUm::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *RestaUm::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_RestaUm.stringdata0))
        return static_cast<void*>(const_cast< RestaUm*>(this));
    return QMainWindow::qt_metacast(_clname);
}

int RestaUm::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QMainWindow::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 18)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 18;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 18)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 18;
    }
    return _id;
}

// SIGNAL 0
void RestaUm::gameOver()
{
    QMetaObject::activate(this, &staticMetaObject, 0, Q_NULLPTR);
}

// SIGNAL 1
void RestaUm::vitoria()
{
    QMetaObject::activate(this, &staticMetaObject, 1, Q_NULLPTR);
}
QT_END_MOC_NAMESPACE
