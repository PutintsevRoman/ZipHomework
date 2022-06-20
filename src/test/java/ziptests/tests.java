package ziptests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.JsonNode;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class tests {

    ClassLoader classLoader = tests.class.getClassLoader();
    @Test
    void zipTest() throws Exception {

        InputStream is = classLoader.getResourceAsStream("1.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;

        while ((entry = zis.getNextEntry()) != null) {

            String name = entry.getName();
           switch (name){
               case "testCSV.csv":
                   CSVReader csvFileReader = new CSVReader(new InputStreamReader(zis, UTF_8));
                   List<String[]> csv = csvFileReader.readAll();
                   assertThat(csv).contains(
                           new String[] {"teacher","lesson","date"},
                           new String[] {"Tuchs","junit","03.06"},
                           new String[] {"Eroshenko","allure","07.06"}
                   );
                   break;
               case "testPDF.pdf":
                   PDF pdf = new PDF(zis);
                   assertThat(pdf.text).contains("A Simple PDF File");
                   break;
               case "TestXLS.xls":
                   XLS xls = new XLS(zis);
                    assertThat(
                      xls.excel.getSheetAt(0)
                                   .getRow(1)
                                   .getCell(1)
                                   .getStringCellValue()
                   ).contains("1.7 Cubic Foot Compact \"Cube\" Office Refrigerators");
                   break;
           }

        }
    }

    @Test
    void jsonTest() throws Exception{

        InputStream is = classLoader.getResourceAsStream("staff.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new InputStreamReader(is, UTF_8));

        assertThat(jsonNode.get("name").asText()).isEqualTo("Valera");
        assertThat(jsonNode.get("age").asInt()).isEqualTo(20);
        assertThat(jsonNode.withArray("skills").toString()).isEqualTo( "[\"Java\",\"Selenide\",\"Python\"]");
        assertThat(jsonNode.get("id").get("number").asInt()).isEqualTo(1234);

    }
}
