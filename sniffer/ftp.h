#ifndef FTP_H
#define FTP_H
#include <QList>
#include <QStandardItem>

void handle_ftp(QList<QStandardItem *> *row, const char *data, uint16_t size);
void handle_ftp_fill(QString *infoStr, const char *data, uint16_t size);

#endif // FTP_H
