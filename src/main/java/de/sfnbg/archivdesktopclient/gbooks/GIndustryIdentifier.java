package de.sfnbg.archivdesktopclient.gbooks;

import lombok.Data;

/*
Hier steckt die ISBN drin
type=ISBN_10 bzw.
type=ISBN_13
Der identifier ist dann die ISBN ohne Leerzeichen und Bindestrichen
 */
@Data
public class GIndustryIdentifier {
    String type;
    String identifier;
}
