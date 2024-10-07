/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author gerst
 */
import java.awt.image.BufferedImage;

public class GraficoBean {

	private BufferedImage grafico;

	public GraficoBean(BufferedImage grafico) {
		this.grafico = grafico;
	}
	
	public BufferedImage getGrafico() {
		return grafico;
	}

	public void setGrafico(BufferedImage grafico) {
		this.grafico = grafico;
	}
}
