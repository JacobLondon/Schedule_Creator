package Backend.FieldDocuments;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Backend.Updateable;

public class IntegerInputDocument extends PlainDocument{

	private int length;
	private Updateable updateable;
	
	public IntegerInputDocument(int length, Updateable update){
		this.length = length;
		this.updateable = update;
	}
	
	public IntegerInputDocument(int length){
		this(length, null);
	}
	
	@Override
	public void insertString(int offSet, String str, AttributeSet attr) throws BadLocationException{
		
		if(str == null)
			return;
		if(getLength() < length){
			if(isNumeric(str)){
				super.insertString(offSet, str, attr);
				if(updateable != null)
					updateable.update();
					System.err.println(this.getLength());
			}
		}
		
	}
	
	public boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	@Override
	public void remove(int offset, int length) throws BadLocationException{
		boolean toUpdate = getLength() > 0 && updateable != null;
		
		super.remove(offset, length);
		
		if(toUpdate){
			updateable.update();
			//System.err.println(this.getLength());
		}
		
	}
}
