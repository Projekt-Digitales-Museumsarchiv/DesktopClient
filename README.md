# DesktopClient

![linux_build](https://github.com/Projekt-Digitales-Museumsarchiv/DesktopClient/actions/workflows/maven-linux.yml/badge.svg?event=push)
![windows_build](https://github.com/Projekt-Digitales-Museumsarchiv/DesktopClient/actions/workflows/maven-windows.yml/badge.svg?event=push)

Der Desktopclient dient zum Erfassung von Büchern anhand einer ISBN

## Workflow

Folgender Workflow ist angedacht:

### Schritt 1: Bild erfassen

Die Bilderfassung soll auf folgenden Wegen möglich sein:

- [x] Drag und Drop aus dem Explorer
- [x] Laden einer Datei (mit File Open Dialog)
- [ ] Scannen eines Bildes (Command Line Tool NN)
- [x] Fotografieren mit der Webcam (mittels JavaCV)
- [x] Exportieren eines Bildes in eine Datei (PNG/JPG)

### Schritt 2: ISBN aus Bild extrahieren

Das vom Barcode oder einer Buchseite aufgenommene Bild soll nun nach einer ISBN durchsucht werden.
Das geschieht

- [ ] bei Barcodes mit ZBar (wenn es keine Maven Dependency gibt mit CLI-Aufruf)
- [ ] bei Fotos von Text (Apache Tika und Tesseract, danach Text beschneiden)

### Schritt 3: Metadaten anhand ISBN ermitteln

Google Books bietet eine API ohne Authentifizierung.
Mittels "GET https://www.googleapis.com/books/v1/volumes?q=isbn:{isbn-Nr}" kann ein Buch gesucht werden.
Im zurückgelieferten Json-Datensatz sollte auch ein Download-Link für ein Cover enthalten sein.

### Schritt 4: Daten in DB schreiben

Es sollte eine Vorwahl per Combobox erfolgen für

- Oberbegriff
- Unterbegriff Ebene 1
- optional Unterbegriff Ebene 2
- optional Unterbegriff Ebene 3
- Raum
- Schrank
- Fach

Damit kombiniert wird ein Datensitz mit den aus Schritt 3 ermittelten Metadaten
