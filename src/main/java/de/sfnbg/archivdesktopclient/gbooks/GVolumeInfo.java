package de.sfnbg.archivdesktopclient.gbooks;

import lombok.Data;

import java.util.List;

@Data
public class GVolumeInfo {
    String title;
    String subtitle;
    List<String> authors;
    String publisher;
    String publishedDate;
    String description;
    List<GIndustryIdentifier> industryIdentifiers;
    Integer pageCount;
    GImageLinks gImageLinks;
    String language;
}
