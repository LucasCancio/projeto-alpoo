package br.unip.alpoo.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FuncoesUteis {

	public static void pintarPaineis(JPanel painel, Color cor) {
		painel.setBackground(cor);
		for (Component filho : painel.getComponents()) {
			filho.setBackground(cor);
			if (filho instanceof JButton) {
				filho.setBackground(Color.WHITE);
			} else if (filho instanceof JPanel) {
				pintarPaineis((JPanel) filho, cor);
			}
		}

	}

	public static void setarFonteEmBotoesDoPainel(JPanel painel, Font font) {
		for (Component filho : painel.getComponents()) {
			if (filho instanceof JButton) {
				filho.setFont(font);
			}
			if (filho instanceof JPanel) {
				setarFonteEmBotoesDoPainel((JPanel) filho, font);
			}
		}
	}

}
