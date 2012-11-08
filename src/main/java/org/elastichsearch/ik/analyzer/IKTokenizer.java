package org.elastichsearch.ik.analyzer;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.elastichsearch.ik.cfx.Configuration;
import org.elastichsearch.ik.core.IKSegmenter;
import org.elastichsearch.ik.core.Lexeme;
import org.elasticsearch.common.settings.Settings;

public class IKTokenizer extends Tokenizer {
	private IKSegmenter _IKImplement;
	private TermAttribute termAtt;
	private OffsetAttribute offsetAtt;
	private int finalOffset;

	public IKTokenizer(Reader in, boolean useSmart,Settings settings) {
		super(in);
		offsetAtt = addAttribute(OffsetAttribute.class);
		termAtt = addAttribute(TermAttribute.class);
		_IKImplement = new IKSegmenter(in, useSmart,new Configuration(settings));
	}

	@Override
	public final boolean incrementToken() throws IOException {

		clearAttributes();
		Lexeme nextLexeme = _IKImplement.next();
		if (nextLexeme != null) {

			termAtt.setTermBuffer(nextLexeme.getLexemeText());

			termAtt.setTermLength(nextLexeme.getLength());

			offsetAtt.setOffset(nextLexeme.getBeginPosition(),
					nextLexeme.getEndPosition());

			finalOffset = nextLexeme.getEndPosition();

			return true;
		}
		return false;
	}

	public void reset(Reader input) throws IOException {
		super.reset(input);
		_IKImplement.reset(input);
	}

	@Override
	public final void end() {

		offsetAtt.setOffset(finalOffset, finalOffset);
	}
}
