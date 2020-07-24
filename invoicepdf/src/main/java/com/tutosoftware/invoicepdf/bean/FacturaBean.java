package com.tutosoftware.invoicepdf.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.tutosoftware.invoicepdf.model.Factura;








@ManagedBean
@SessionScoped
public class FacturaBean implements Serializable {
	
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cantidad;
	private Date fechaServicio;
	private String concepto;
	private double precioUnitario;
	private double descuento;
	private double total;
	private static ArrayList<Factura> conceptos = new ArrayList<Factura>();
    private double subTotal;
    private double impuestoMunicipal;
    private double impuestoEstatal;
    private double impuestoTotal;
    private double totalFactura;
    

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaServicio() {
		return fechaServicio;
	}

	public void setFechaServicio(Date fechaServicio) {
		this.fechaServicio = fechaServicio;
	}


	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}



	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

    


	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	public ArrayList<Factura> getConceptos() {
		return conceptos;
	}
	
	
	
	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getImpuestoMunicipal() {
		return impuestoMunicipal;
	}

	public void setImpuestoMunicipal(double impuestoMunicipal) {
		this.impuestoMunicipal = impuestoMunicipal;
	}

	public double getImpuestoEstatal() {
		return impuestoEstatal;
	}

	public void setImpuestoEstatal(double impuestoEstatal) {
		this.impuestoEstatal = impuestoEstatal;
	}

	public double getImpuestoTotal() {
		return impuestoTotal;
	}

	public void setImpuestoTotal(double impuestoTotal) {
		this.impuestoTotal = impuestoTotal;
	}

	public double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public void agregarConcepto() {
		
		total=(cantidad * precioUnitario)-descuento;
		Factura factura = new Factura(cantidad,fechaServicio,concepto,precioUnitario,descuento,total);
		conceptos.add(factura);
		subTotal=subTotal+total;
		impuestoMunicipal=(subTotal/100)*1;
		impuestoEstatal=(subTotal/100)*10.50;
		impuestoTotal=impuestoMunicipal+impuestoEstatal;
		totalFactura=subTotal+impuestoTotal;
		cantidad=0;
		concepto="";
		precioUnitario=0.0;
		descuento=0.0;
		fechaServicio=null;
	}
	
	
	

	public void imprimir() {
		
		Document document = new Document(PageSize.LETTER);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			
			Date hoy = new Date();
			String fechaHoy = fechaFormato(hoy);
			
			
			
			PdfWriter writer= PdfWriter.getInstance(document, baos);
			document.open();
			Font fuente1 = new Font();
			Font fuente2 = new Font();
			fuente1.setSize(20);
			fuente2.setSize(10);
			int numeroFactura=2020;
			document.add(new Paragraph(" FACTURA DE SERVICIO \n",fuente1));
			document.add(new Paragraph("Número de factura:       "+numeroFactura+"\n",fuente2));
			document.add(new Paragraph("Fecha de factura:        "+fechaHoy+"\n",fuente2));
			document.add(new Paragraph("Dirección: Noche Buena#21,San Jose el Jaral,Atizapan de Zaragoza \n",fuente2));
			document.add(new Paragraph("Estado de Mexico,Mexico cp:52924 \n",fuente2));
			document.add(new Paragraph("Telefono: 5540583258\n",fuente2));
			document.add(new Paragraph("Email: kapo1978@hotmail.com\n",fuente2));
			LineSeparator linea = new LineSeparator();
			linea.setOffset(-8.0f);
			linea.setAlignment(Element.ALIGN_LEFT);
			linea.setPercentage(70.0f);
			document.add(linea);
			document.add(new Paragraph("\nCobre a:                                                                     "+
					        "Envie a:\n",fuente2));
			document.add(new Paragraph("Edwin Blancovich                                                               "+
			        "Edwin Blancovich\n",fuente2));
			document.add(new Paragraph("\n\n\nAguadilla,PR 0605\n",fuente2));
			document.add(new Paragraph("                                                                               "+
			        "Telefono:                                  Fax:\n",fuente2));
			Image imagen = Image.getInstance(getClass().getResource("/com/tutosoftware/invoicepdf/image/tuto.jpg"));
			imagen.setAbsolutePosition(400f, 650f);
			document.add(imagen);
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100.0f);
			table.setWidths(new float[] {2.0f,2.0f,2.0f,2.0f});
			table.setSpacingBefore(10);
			
			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			font.setSize(10);
			font.setColor(CMYKColor.BLACK);
			
			// define table header cell
			PdfPCell cell = new PdfPCell();
			PdfPCell cell2 = new PdfPCell();
			cell.setBackgroundColor(CMYKColor.CYAN);
			cell.setPadding(5);
			
			// write table header 
			cell.setPhrase(new Phrase("Nombre Representativo", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Fecha de entrega", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Contacto", font));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Terminos", font));
			table.addCell(cell);
			
			
			
			cell2.setBorder(Rectangle.NO_BORDER);
	        cell2.setPhrase(new Phrase("REP 1",font));
			table.addCell(cell2);			
			
			cell2.setPhrase(new Phrase(fechaHoy,font));
			table.addCell(cell2);
			
			cell2.setPhrase(new Phrase("----------",font));
			table.addCell(cell2);
			
			cell2.setPhrase(new Phrase("Net 30",font));
			table.addCell(cell2);
			
			document.add(table);
			
			PdfPTable table2 = new PdfPTable(6);
			table2.setWidthPercentage(100.0f);
			table2.setWidths(new float[] {1.2f,2.0f,3.0f,2.0f,2.0f,2.0f});
			table2.setSpacingBefore(10);
			
			cell.setPhrase(new Phrase("Cantidad", font));
			table2.addCell(cell);
			
			cell.setPhrase(new Phrase("Fecha Servicio", font));
			table2.addCell(cell);
			
			cell.setPhrase(new Phrase("Descripción", font));
			table2.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Precio", font));
			table2.addCell(cell);
			
			cell.setPhrase(new Phrase("Descuento", font));
			table2.addCell(cell);
			
			cell.setPhrase(new Phrase("Total", font));
			table2.addCell(cell);
			
			
			for(Factura f : conceptos) {
				cell2.setPhrase(new Phrase(f.getCantidad()+"",font));
				table2.addCell(cell2);
				
				cell2.setPhrase(new Phrase(fechaFormato(f.getFechaServicio()),font));
				table2.addCell(cell2);
				
				cell2.setPhrase(new Phrase(f.getConcepto(),font));
				table2.addCell(cell2);
				
				
				cell2.setPhrase(new Phrase(f.getPrecioUnitario()+"",font));
				table2.addCell(cell2);
				
				
				cell2.setPhrase(new Phrase(f.getDescuento()+"",font));
				table2.addCell(cell2);
				
				
				cell2.setPhrase(new Phrase(f.getTotal()+"",font));
				table2.addCell(cell2);
				
			}
			
			
			
			
			
			document.add(table2);
			
			LineSeparator linea2 = new LineSeparator();
			linea2.setOffset(-8.0f);
			linea2.setPercentage(100.0f);
			document.add(linea2);
			document.add(new Paragraph("\nPolítica de devolución:\n",fuente2));
			document.add(new Paragraph("1.Piezas correctamente vendidas no tienen devolución.\n",fuente2));
			document.add(new Paragraph("2.Pedidos especiales requieren depósito, este no sera reembolsable y la pieza no tiene devolución.\n",fuente2));
			document.add(new Paragraph("3.Toda reclamación debe estar acompañada de la factura original y no debe pasar de 10 dias de ser facturada.\n",fuente2));
			document.add(new Paragraph("4.No seremos responsables por mercancía por mas de 10 dias laborables.\n",fuente2));
			document.add(new Paragraph("5.Piezas eléctricas no tendrán garantía ni devolución.\n",fuente2));
			document.add(new Paragraph("\n\n\n",fuente2));
			Font font1 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
			document.add(new Paragraph("                                                                                  "+
			"                 SubTotal:         $"+subTotal,font1));
			document.add(new Paragraph("\n                                                                                  "+
		    "                 Impuestos:       Municipal 1%  $"+impuestoMunicipal,font1));
			document.add(new Paragraph("\n                                                                                  "+
		    "                                      Estatal 10.50%  $"+impuestoEstatal,font1));
			document.add(new Paragraph("\n                                                                                  "+
		    "                                      Impuesto Total 11.50%  $"+impuestoTotal,font1));
			document.add(new Paragraph("\n                                                                                  "+
			"             Total factura:           $"+totalFactura,font1));
			/*
			PdfContentByte cb = writer.getDirectContent();
			cb.saveState(); 
			cb.setColorStroke(CMYKColor.BLACK); 
			cb.rectangle(330,200,200,100);
			cb.rectangle(330,140,200,60);
		
			cb.stroke(); 
			cb.restoreState();
			*/
			
			
			
		}catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
		
		document.close();
		FacesContext context = FacesContext.getCurrentInstance();
		Object response = context.getExternalContext().getResponse();
		if (response instanceof HttpServletResponse) {
            HttpServletResponse hsr = (HttpServletResponse) response;
            hsr.setContentType("application/pdf");
            hsr.setHeader("Content-disposition", "attachment;filename=/factura.pdf");
            hsr.setContentLength(baos.size());
            try {
                  ServletOutputStream out = hsr.getOutputStream();
                  baos.writeTo(out);
                  out.flush();
            } catch (IOException ex) {
                  System.out.println("Error:  " + ex.getMessage());
            }
            context.responseComplete();
      }
		
		
		
	}
	
	public void limpiarFactura() {
		conceptos.clear();
		subTotal=0.0;
		impuestoMunicipal=0.0;
		impuestoEstatal=0.0;
		impuestoTotal=0.0;
		totalFactura=0.0;
	}
	
	public String fechaFormato(Date fecha) {
		String cadenaFecha;
		
		Calendar cal= Calendar.getInstance();
		cal.setTime(fecha);
		int dia=cal.get(Calendar.DAY_OF_MONTH);
		int mes=cal.get(Calendar.MONTH)+1;
		int year=cal.get(Calendar.YEAR);
		
		String mesString;
		
		switch (mes) {
        case 1:  mesString = "Enero";
                 break;
        case 2:  mesString  = "Febrero";
                 break;
        case 3:  mesString = "Marzo";
                 break;
        case 4:  mesString = "Abril";
                 break;
        case 5:  mesString = "Mayo";
                 break;
        case 6:  mesString = "Junio";
                 break;
        case 7:  mesString = "Julio";
                 break;
        case 8:  mesString = "Agosto";
                 break;
        case 9:  mesString = "Septiembre";
                 break;
        case 10: mesString = "Octubre";
                 break;
        case 11: mesString = "Noviembre";
                 break;
        case 12: mesString = "Diciembre";
                 break;
        default: mesString = "Invalid month";
                 break;
        }
		
		
		cadenaFecha=dia+"/"+mesString+"/"+year;
		
		
		return cadenaFecha; 
	}
	
	
	
	
	
	
	

}
