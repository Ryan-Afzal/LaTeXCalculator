package com.ryanafzal.io.calculator.render;

import java.awt.image.BufferedImage;

import org.w3c.dom.Document;

import com.ryanafzal.io.calculator.resources.misc.ILaTeXValue;

import net.sourceforge.jeuclid.LayoutContext;
import net.sourceforge.jeuclid.MathMLParserSupport;
import net.sourceforge.jeuclid.context.LayoutContextImpl;
import net.sourceforge.jeuclid.converter.Converter;
import uk.ac.ed.ph.snuggletex.SnuggleEngine;
import uk.ac.ed.ph.snuggletex.SnuggleInput;
import uk.ac.ed.ph.snuggletex.SnuggleSession;

public class Render {
	
	private SnuggleEngine engine;
	
	/**
	 * Creates a new Render
	 */
	public Render() {
		this.initialize();
	}
	
	private boolean initialize() {
		try {
			this.engine = new SnuggleEngine();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Generates a {@code BufferedImage} representing the LaTeX representation of the provided {@code String}
	 * @param TeXInput LaTeX representation of an equation.
	 * @return Returns a {@code BufferedImage} representing the provided {@code String}.
	 * @throws RenderException
	 */
	public BufferedImage getRenderedImage(String TeXInput) throws RenderException {
		try {
			SnuggleSession session = this.engine.createSession();
			
			session.parseInput(new SnuggleInput(TeXInput));
			
			Document document = MathMLParserSupport.parseString(session.buildXMLString());
			return Converter.getInstance().render(document, LayoutContextImpl.getDefaultLayoutContext());
		} catch (Exception e) {
			throw new RenderException("Error rendering LaTeX", e);
		}
	}
	
	public BufferedImage getRenderedImage(ILaTeXValue value) throws RenderException {
		return getRenderedImage(value.getLaTeXString());
	}
	
}
