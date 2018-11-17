package main;


import com.itextpdf.io.IOException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class main {


    public static void main(String[] args) throws IOException, java.io.IOException{

        String number = "2018/11/0001";

        User user = new User("Jakub Górnicki");

        List<SellObject> list = new LinkedList<>();
        list.add(new SellObject("Telefon", new BigDecimal("4.00"), "szt.", new BigDecimal("1500.00"), 23));
        list.add(new SellObject("Laptop", new BigDecimal("3.00"), "szt.", new BigDecimal("2500.00"), 0));
        list.add(new SellObject("Klawiatura", new BigDecimal("5.00"), "szt.", new BigDecimal("50.00"), 5));
        list.add(new SellObject("Monitor", new BigDecimal("2.00"), "szt.", new BigDecimal("550.00"), 23));
        list.add(new SellObject("Biurko", new BigDecimal("1.00"), "szt.", new BigDecimal("177299.99"), 8));
        list.add(new SellObject("Telefon", new BigDecimal("4.00"), "szt.", new BigDecimal("1500.00"), 23));
        list.add(new SellObject("Laptop", new BigDecimal("3.00"), "szt.", new BigDecimal("2500.00"), 8));
        list.add(new SellObject("Klawiatura", new BigDecimal("5.00"), "szt.", new BigDecimal("50.00"), 5));
        list.add(new SellObject("Monitor", new BigDecimal("2.00"), "szt.", new BigDecimal("550.00"), 23));
        list.add(new SellObject("Biurko", new BigDecimal("1.00"), "szt.", new BigDecimal("1299.99"), 23));
        list.add(new SellObject("Telefon", new BigDecimal("4.00"), "szt.", new BigDecimal("1500.00"), 0));
        list.add(new SellObject("Laptop", new BigDecimal("3.00"), "szt.", new BigDecimal("2500.00"), 8));
        list.add(new SellObject("Klawiatura", new BigDecimal("5.00"), "szt.", new BigDecimal("50.00"), 23));
        list.add(new SellObject("Monitor", new BigDecimal("2.00"), "szt.", new BigDecimal("550.00"), 23));
        list.add(new SellObject("Biurko", new BigDecimal("1.00"), "szt.", new BigDecimal("1299.99"), 23));

        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD, PdfEncodings.CP1250, true);

        Campany campany = new Campany("Smartfon Serwis Jakub Górnicki",
                "Jaśminowa 18",
                "49-300 Brzeg",
                "7471794167");
        Campany campany1 = new Campany("Sklep komputerowy Jan kowalski",
                "Rynek 26", "49-306 Brzeg", "999999999");


        PdfDocument pdf = new PdfDocument(new PdfWriter("Faktura"+number.replace("/","")+".pdf"));

        Document document = new Document(pdf, PageSize.A4);
        pdf.addNewPage(1);
        PdfCanvas canvas = new PdfCanvas(pdf.getPage(1));

        new Canvas(canvas, pdf, new Rectangle(180, 800, 250, 20))
                .add(new Paragraph("FAKTURA VAT nr "+number).setFontSize(16));
        new Canvas(canvas, pdf, new Rectangle(465, 770, 100, 50))
                .add(getDateTable().setFont(bold).setFontSize(8));

        new Canvas(canvas, pdf, new Rectangle(35, 600, 200, 100))
                .add((getCampanyTable(campany, "Sprzedawca")).setFont(bold));
        new Canvas(canvas, pdf, new Rectangle(315, 600, 200, 100))
                .add((getCampanyTable(campany1, "Nabywca")).setFont(bold));

        new Canvas(canvas, pdf, new Rectangle(35, 150, 530, 450))
                .add(getMainTable(list).setFont(bold)).add(getVatTable(list).setFont(bold)).add(getSumTable(list).setFont(bold).setFontSize(10));
        new Canvas(canvas, pdf, new Rectangle(35, 30, 200, 120))
                .add(getSignTable(user).setFont(bold).setFontSize(8));
        new Canvas(canvas, pdf, new Rectangle(315, 30, 200, 120))
                .add(getSignTable().setFont(bold).setFontSize(8));
        document.close();
    }

    private static Table getCampanyTable(Campany campany, String role) {

        Table table = new Table(1)
                .setWidth(250)
                .setFontSize(8)
                .setBorder(new SolidBorder(Color.GRAY, 0.5F));
        Cell sn = new Cell()
                .add(role)
                .setFontColor(Color.WHITE)
                .setFontSize(10)
                .setHeight(15)
                .setBackgroundColor(Color.GRAY);
        table.addCell(sn);
        Cell name = new Cell()
                .add(campany.getName() + "\n" + campany.getAdressLine1() + "\n" + campany.getAdressLine2() + "\nNIP:" + campany.getNip())
                .setHeight(48);
        table.addCell(name);
        return table;
    }

    private static Table getMainTable(List<SellObject> list) {

        Table table = new Table(new float[]{20, 170, 30, 30, 70, 30, 65, 50, 65})
                .setFontSize(8)
                .setBorder(new SolidBorder(Color.GRAY, 0.5F))
                .setTextAlignment(TextAlignment.CENTER)
                .setWidth(530);

        Cell lp = new Cell().add("Lp").setBackgroundColor(Color.LIGHT_GRAY);
        Cell name = new Cell().add("Nazwa").setBackgroundColor(Color.LIGHT_GRAY);
        Cell quantity = new Cell().add("Ilość").setBackgroundColor(Color.LIGHT_GRAY);
        Cell unit = new Cell().add("j.m.").setBackgroundColor(Color.LIGHT_GRAY);
        Cell unitNettoPrice = new Cell().add("Cena jedn. netto").setBackgroundColor(Color.LIGHT_GRAY);
        Cell vat = new Cell().add("VAT").setBackgroundColor(Color.LIGHT_GRAY);
        Cell nettoPrice = new Cell().add("Wartość netto").setBackgroundColor(Color.LIGHT_GRAY);
        Cell vatAmount = new Cell().add("Kwota VAT").setBackgroundColor(Color.LIGHT_GRAY);
        Cell bruttoPeice = new Cell().add("Wartość brutto").setBackgroundColor(Color.LIGHT_GRAY);

        table
                .addCell(lp)
                .addCell(name)
                .addCell(quantity)
                .addCell(unit)
                .addCell(unitNettoPrice)
                .addCell(vat)
                .addCell(nettoPrice)
                .addCell(vatAmount)
                .addCell(bruttoPeice);

        for (int i = 0; i < list.size(); i++) {
            Cell lp1 = new Cell().add(String.valueOf(i + 1));
            Cell name1 = new Cell().add(list.get(i).name);
            Cell quantity1 = new Cell().add(String.valueOf(list.get(i).quantity));
            Cell unit1 = new Cell().add(list.get(i).unit);
            Cell unitNettoPrice1 = new Cell().add(String.valueOf(list.get(i).unitNettoPrice));
            Cell vat1 = new Cell().add(String.valueOf(list.get(i).vat) + "%");
            Cell nettoPrice1 = new Cell().add(String.valueOf(list.get(i).nettoPrice));
            Cell vatAmount1 = new Cell().add(String.valueOf(list.get(i).vatAmount));
            Cell bruttoPeice1 = new Cell().add(String.valueOf(list.get(i).bruttoPeice));
            table
                    .addCell(lp1)
                    .addCell(name1)
                    .addCell(quantity1)
                    .addCell(unit1)
                    .addCell(unitNettoPrice1)
                    .addCell(vat1)
                    .addCell(nettoPrice1)
                    .addCell(vatAmount1)
                    .addCell(bruttoPeice1);
        }

        return table;

    }

    private static Table getVatTable(List<SellObject> list) {
        Table table = new Table(new float[]{65, 65, 65, 65})
                .setFontSize(8)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setWidth(260)
                .setHorizontalAlignment(HorizontalAlignment.RIGHT);
        Cell space = new Cell(1, 4).setBorder(Border.NO_BORDER).setHeight(20);
        Cell cell = new Cell().add("Stawka VAT").setBackgroundColor(Color.LIGHT_GRAY);
        Cell cell1 = new Cell().add("Wartość netto").setBackgroundColor(Color.LIGHT_GRAY);
        Cell cell2 = new Cell().add("Kwota VAT").setBackgroundColor(Color.LIGHT_GRAY);
        Cell cell3 = new Cell().add("Wartość brutto").setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(space).addCell(cell).addCell(cell1).addCell(cell2).addCell(cell3);

        List<Integer> vat = list.stream().map(SellObject::getVat).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (Integer i : vat) {
            Cell c1 = new Cell().add(String.valueOf(i)+"%");
            List<SellObject> listByVat = list.stream().filter(s -> s.getVat() == i).collect(Collectors.toList());
            Cell c2 = new Cell().add(String.valueOf(listByVat
                    .stream()
                    .map(SellObject::getNettoPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)));
            Cell c3 = new Cell().add(String.valueOf(listByVat
                    .stream()
                    .map(SellObject::getVatAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)));
            Cell c4 = new Cell().add(String.valueOf(listByVat
                    .stream()
                    .map(SellObject::getBruttoPeice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)));
            table
                    .addCell(c1)
                    .addCell(c2)
                    .addCell(c3)
                    .addCell(c4);
        }
        return table;
    }

    private static Table getSumTable(List<SellObject> list) {
        Table table = new Table(new float[]{120, 80})
                .setTextAlignment(TextAlignment.CENTER)
                .setHorizontalAlignment(HorizontalAlignment.RIGHT);
        Cell space = new Cell(1, 2).setBorder(Border.NO_BORDER).setHeight(20);
        Cell c = new Cell().add("RAZEM DO ZAPŁATY:").setBackgroundColor(Color.LIGHT_GRAY);
        Cell c1 = new Cell().add(String.valueOf(list.stream()
                .map(SellObject::getBruttoPeice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)) + " PLN")
                .setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(space).addCell(c).addCell(c1);
        return table;
    }

    private static Table getDateTable() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Table table = new Table(1)
                .setWidth(100)
                .setTextAlignment(TextAlignment.CENTER);
        Cell c = new Cell().add("Data wystawienia").setBackgroundColor(Color.LIGHT_GRAY);
        Cell c1 = new Cell().add(String.valueOf(df.format(new Date(System.currentTimeMillis()))));

        table.addCell(c).addCell(c1);
        return table;
    }

    private static Table getSignTable(User user){
        Table table = new Table(1)
                .setWidth(250)
                .setFontSize(8)
                .setBorder(new SolidBorder(Color.GRAY, 0.5F))
                .setTextAlignment(TextAlignment.CENTER);
        Cell c = new Cell().add("Wystawił(a)").setBackgroundColor(Color.LIGHT_GRAY);
        Cell c1 = new Cell()
                .add(user.getName())
                .setHeight(80);

        table.addCell(c).addCell(c1);
        return table;
    }
    private static Table getSignTable(){
        Table table = new Table(1)
                .setWidth(250)
                .setFontSize(8)
                .setBorder(new SolidBorder(Color.GRAY, 0.5F))
                .setTextAlignment(TextAlignment.CENTER);;
        Cell c = new Cell().add("Odebrał(a)").setBackgroundColor(Color.LIGHT_GRAY);
        Cell c1 = new Cell()
                .setHeight(80);

        table.addCell(c).addCell(c1);
        return table;
    }
}


