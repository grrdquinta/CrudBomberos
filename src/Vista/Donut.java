/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;

public class Donut {

public static void main(String[] args) {
Donut donut = new Donut();
}

public Donut() {

DefaultPieDataset dataset = new DefaultPieDataset();
dataset.setValue("A", 50);
dataset.setValue("B", 25);
dataset.setValue("C", 25);

JFreeChart chart = ChartFactory.createRingChart("Gráfico", dataset, true, false, false);

RingPlot ringplot = (RingPlot) chart.getPlot();
ringplot.setLabelFont(new Font("SansSerif", 0, 12));
ringplot.setSectionDepth(0.34999999999999998D);
ringplot.setCircular(true);
ringplot.setLabelGap(0.02D);
ringplot.setBackgroundPaint(Plot.DEFAULT_BACKGROUND_PAINT);
ringplot.setShadowPaint(Color.WHITE);
ringplot.setSectionPaint("A", new Color(179, 181, 184));
ringplot.setSectionPaint("B", new Color(255, 51, 51));
ringplot.setSectionPaint("C", new Color(136, 160, 205));
ringplot.setOutlinePaint(null);

BufferedImage img = new BufferedImage(350, 350, BufferedImage.TYPE_INT_ARGB);
Graphics2D g2 = img.createGraphics();
chart.draw(g2, new Rectangle2D.Double(0, 0, 350, 350));
g2.dispose();

GraficoBean bean = new GraficoBean(img);

List charts = new ArrayList();
charts.add(bean);

Map parametros = new HashMap();

try {
JasperReport report = JasperCompileManager.compileReport("src/relatorios/relatorio.jrxml");
JasperPrint print = JasperFillManager.fillReport(report,parametros, new JRBeanCollectionDataSource(charts, false));
JasperExportManager.exportReportToPdfFile(print,"src/relatorios/grafico.pdf");
} catch (Exception e) {
e.printStackTrace();
}

}

}
