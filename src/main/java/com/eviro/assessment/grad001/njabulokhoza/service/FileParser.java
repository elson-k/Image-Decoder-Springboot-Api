package com.eviro.assessment.grad001.njabulokhoza.service;

import java.io.File;
import java.net.URI;

public interface FileParser {
    void parseCSV (File csvFile);
    File convertCSVDataToImage(String imageFormat,String base64ImageData);
    URI createImageLink (File fileImage);
}
