package com.plataforma.myp7.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.PdfExporterConfiguration;

public class Relatorio {
	
	private final static Logger log = Logger.getLogger(Relatorio.class);
	
	private static final String DIR_REPORT = "/com/plataforma/myp7/report/";
	private static final String DIR_IMAGES_REPORT = "/com/plataforma/myp7/report/images/";
	
	/**
	 * Gera um PDF no response
	 * 
	 * @param params Parametros do Relatorio
	 * @param lista Collection<?> a ser usada nos registros
	 * @param report Nome do Relatorio
	 * @param res HttpServletResponse
	 * 
	 */
	public static void gerar(Map<String, Object> params, Collection<?> lista, String report, HttpServletResponse res){
		byte[] bytes = null;
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(lista);

		StringBuilder rel = new StringBuilder();
		rel.append(DIR_REPORT).append(report).append(".jasper");
		
		InputStream arquivo = Relatorio.class.getResourceAsStream(rel.toString());

		params.put("SUBREPORT_DIR", DIR_REPORT);
		params.put(PdfExporterConfiguration.PROPERTY_PDF_JAVASCRIPT, "this.zoom = 50;");
		
		try {
			bytes = JasperRunManager.runReportToPdf(arquivo, params, dataSource);
			int len = bytes.length;
			
			res.setContentType("application/pdf");
		    res.setContentLength(len);
		    res.setHeader("Content-Disposition", String.format("inline:filename=%s.pdf", "reportSaida"));
		    
			OutputStream ouputStream = res.getOutputStream();
			ouputStream.write(bytes, 0, len);
			ouputStream.flush();
			ouputStream.close();
			
		} catch (JRException | IOException e) {
			log.error("Relatorio.gerar", e);
		}
	}
	
	public static void setImageParam(Map<String, Object> params, String nameParam, String image) {
		ImageIcon imageIcon = new ImageIcon(Relatorio.class.getResource(DIR_IMAGES_REPORT+image));
		params.put(nameParam, imageIcon.getImage());
	}
}
