#ifndef TELNET_H
#define TELNET_H
#include <QList>
#include <QStandardItem>

void handle_telnet(QList<QStandardItem *> *row, const char *data, uint16_t size);
void handle_telnet_fill(QString *infoStr, const char *data, uint16_t size);

#endif // TELNET_H
