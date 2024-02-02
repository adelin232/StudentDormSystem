package studentsdorm.platform;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.Setter;
import studentsdorm.platform.Student.Student;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Setter
public class PDFGenerator {

    private List<Student> students;

    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4.rotate());

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to modify it
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD);
        fontTitle.setSize(20);

        // Creating paragraph
        Paragraph paragraph = new Paragraph("Studenti", fontTitle);

        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in document
        document.add(paragraph);

        document.add(new Paragraph("\n"));

        // Creating a table of 7 columns
        PdfPTable table = new PdfPTable(7);

        // Setting width of table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 1, 3, 2, 3, 4, 2, 2 });
        table.setSpacingBefore(5);

        // Create Table Cells for table header
        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(4);

        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setColor(CMYKColor.WHITE);

        // Adding headings in the created table cell/ header
        // Adding Cell to table
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Room", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Phone", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Average Grade", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("English Certificate", font));
        table.addCell(cell);

        // Iterating over the list of students
        for (Student student : students) {
            table.addCell(String.valueOf(student.getId()));
            table.addCell(student.getName());
            table.addCell(String.valueOf(student.getRoom()));
            table.addCell(student.getPhone());
            table.addCell(student.getEmail());
        }
        // Adding the created table to document
        document.add(table);

        // Closing the document
        document.close();
    }
}
