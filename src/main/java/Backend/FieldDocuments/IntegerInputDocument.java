package Backend.FieldDocuments;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Backend.Updateable;

/**
 * This is a document that will verify that input is an integer. 
 * This should be used with JTextFields or similar text areas.
 * 
 */
public class IntegerInputDocument extends PlainDocument{

	private int length;
	private Updateable updateable;
	
	/**
	 * The constructor that includes listener object object.
	 * 
	 * @param length The length of the integer that is allowed for input.
	 * @param update A listener that will have the update method called whenever the document is updated.
	 */
	public IntegerInputDocument(int length, Updateable update){
		this.length = length;
		this.updateable = update;
	}
	
	/**
	 * The constructor without a listener.
	 * 
	 * @param length The length of the integer that is allowed for input.
	 */
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
			}
		}
		
	}
	
	/**
	 * This is a helper method to check if a string is numeric.
	 * 
	 * @param str The string to check
	 * @return Returns true if the string is numeric, false otherwise.
	 */
	private boolean isNumeric(String str)
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
