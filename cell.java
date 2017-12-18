
public class cell {
	int value;
	int row;
	int col;
	int group;
	int subrow;
	int subcol;

	cell(int newVal) {
		value = newVal;
	}

	void setValue (int newVal) {
	    value = newVal;
	}

	void setRow (int newRow) {
	    row = newRow;
	}

	void setCol (int newCol) {
	    col = newCol;
	}

	void setGroup (int newGroup) {
	    group = newGroup;
	}

	void setSubrow (int newN) {
	    subrow = newN;
	}

	void setSubcol (int newM) {
	    subcol = newM;
	}

	int getRow() {
	    return row;
	}

	int getCol() {
	    return col;
	}

	int getSubrow() {
	    return subrow;
	}

	int getSubcol() {
	    return subcol;
	}

	int getGroup() {
	    return group;
	}

	int getValue() {
	    return value;
	}
}
