package studentsdorm.platform;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.Setter;
import studentsdorm.platform.Student.StudentEntity;
import studentsdorm.platform.Student.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Setter
public class PDFGenerator {

    private StudentService studentService;

    public void generate(HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to modify it
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD);
        fontTitle.setSize(20);

        // Creating paragraph
        Paragraph paragraph = new Paragraph("Students", fontTitle);

        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in document
        document.add(paragraph);

        document.add(new Paragraph("\n"));

        // Creating a table of 3 columns
        PdfPTable table = new PdfPTable(2);

        // Setting width of table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 3, 3 });
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
        cell.setPhrase(new Phrase("Nume", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Camera", font));
        table.addCell(cell);

        // Iterating over the list of students
        for (StudentEntity student : studentService.getStudents()) {
            table.addCell(String.valueOf(student.getId()));
            table.addCell(student.getName());
            table.addCell(String.valueOf(student.getRoom()));
        }
        // Adding the created table to document
        document.add(table);

        // Closing the document
        document.close();
    }
}
