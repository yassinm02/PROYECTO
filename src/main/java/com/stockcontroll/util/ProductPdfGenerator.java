package com.stockcontroll.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stockcontroll.model.InventarioProducto;
import com.stockcontroll.model.Producto;
import com.stockcontroll.model.Proveedor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductPdfGenerator {

    public static Document generatePdfProducts(List<Producto> productos, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
        Paragraph titleParagraph = new Paragraph("Lista de productos", fontTitle);
        titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titleParagraph);

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3, 3, 3, 3, 3, 3, 3, 3, 3});
        table.setSpacingBefore(10);

        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(BaseColor.BLUE);
        headerCell.setPadding(5);

        headerCell.setPhrase(new Phrase("Nombre", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Descripción", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Precio base", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Estado", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Tipo IVA", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Fecha Creación", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Proveedor", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Código Barras", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Cantidad", fontHeader));
        table.addCell(headerCell);

        Font fontData = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        PdfPCell dataCell = new PdfPCell();
        dataCell.setPadding(5);

        for (Producto producto : productos) {
            table.addCell(createDataCell((producto.getName().isEmpty() ? "No tiene nombre": producto.getName()), fontData));
            table.addCell(createDataCell((producto.getDescripcion() == null ? "Sin descripción" : producto.getDescripcion()), fontData));
            table.addCell(createDataCell((String.valueOf(producto.getPrecioBase())), fontData));
            table.addCell(createDataCell((producto.getEstado().isEmpty() ? "No tiene estado" : producto.getEstado()), fontData));
            table.addCell(createDataCell((producto.getTipoIva().getName() == null ? "Sin tipo iva" : producto.getTipoIva().getName()), fontData));
            table.addCell(createDataCell(producto.getFechaCreacion().toString(), fontData));
            table.addCell(createDataCell((producto.getProveedor() == null ? "N/A" : producto.getProveedor().getName()), fontData));
            table.addCell(createDataCell((producto.getCodBarras() == null ? "Sin codigode barras" : producto.getCodBarras()), fontData));
            table.addCell(createDataCell((String.valueOf(producto.getCantidad())), fontData));
        }

        document.add(table);
        document.close();

        return document;
    }

    private static PdfPCell createDataCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(5);
        return cell;
    }

    public static Document generatePdfProveedores(List<Proveedor> proveedors, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
        Paragraph titleParagraph = new Paragraph("Lista de empresas", fontTitle);
        titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titleParagraph);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3, 3, 3, 3, 3, 3});
        table.setSpacingBefore(10);

        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(BaseColor.BLUE);
        headerCell.setPadding(5);

        headerCell.setPhrase(new Phrase("Nombre", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Dirección", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Teléfono", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Email", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Sitio Web", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Observaciones", fontHeader));
        table.addCell(headerCell);

        Font fontData = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        PdfPCell dataCell = new PdfPCell();
        dataCell.setPadding(5);

        for (Proveedor proveedor : proveedors) {
            table.addCell(createDataCell((proveedor.getName() == null ? "Vacío" : proveedor.getName()), fontData));
            table.addCell(createDataCell((proveedor.getAddress() == null ? "Vacío" : proveedor.getAddress()), fontData));
            table.addCell(createDataCell((proveedor.getPhone() == null ? "Vacío" : proveedor.getPhone()), fontData));
            table.addCell(createDataCell((proveedor.getEmail() == null ? "Vacío" : proveedor.getEmail()), fontData));
            table.addCell(createDataCell((proveedor.getWebsite() == null ? "Vacío" : proveedor.getWebsite()), fontData));
            table.addCell(createDataCell((proveedor.getObservations() == null ? "Vacío" : proveedor.getObservations()), fontData));
        }


        document.add(table);
        document.close();

        return document;
    }

    public static Document generatePdfInventarioProductos(List<InventarioProducto> inventarioProductos, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
        Paragraph titleParagraph = new Paragraph("Lista de productos de inventario", fontTitle);
        titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titleParagraph);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3, 3, 3, 3});
        table.setSpacingBefore(10);

        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(BaseColor.BLUE);
        headerCell.setPadding(5);


        headerCell.setPhrase(new Phrase("Inventario", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Producto", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Cantidad producto", fontHeader));
        table.addCell(headerCell);
        headerCell.setPhrase(new Phrase("Revisado", fontHeader));
        table.addCell(headerCell);

        Font fontData = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        PdfPCell dataCell = new PdfPCell();
        dataCell.setPadding(5);

        for (InventarioProducto inventarioProducto : inventarioProductos) {
            table.addCell(createDataCell(inventarioProducto.getInventario().getFechaCreacion().toString(), fontData));
            table.addCell(createDataCell(inventarioProducto.getProducto().getName(), fontData));
            table.addCell(createDataCell(String.valueOf(inventarioProducto.getProducto().getCantidad()), fontData));
            table.addCell(createDataCell(inventarioProducto.isRevisado() ? "Sí" : "No", fontData));
        }

        document.add(table);
        document.close();

        return document;
    }




}



