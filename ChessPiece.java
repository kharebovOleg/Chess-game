public abstract class ChessPiece {
    String color;
    boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public abstract String getColor();

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    protected boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    protected int getMax(int a, int b) {
        return Math.max(a, b);
    }

    protected int getMin(int a, int b) {
        return Math.min(a, b);
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        if (checkPos(line) && checkPos(column)) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (chessBoard.board[i][j] != null) {
                        if (!chessBoard.board[i][j].getColor().equals(color) && chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, line, column)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else return true;
    }

    public boolean moveLikeBishop(ChessBoard chessBoard, int line, int column, int toLine, int toColumn){
        if (!chessBoard.board[line][column].equals(this)) return false;
        if ((column == getMin(column, toColumn) && line == getMax(line, toLine)) ||
                (toColumn == getMin(column, toColumn) && toLine == getMax(line, toLine))) {

            int fromL = getMax(line, toLine);
            int fromC = getMin(column, toColumn);
            int toL = getMin(line, toLine);
            int toC = getMax(column, toColumn);

            int[][] positions = new int[toC - fromC][1];

            for (int i = 1; i < toC - fromC; i++) {

                if (chessBoard.board[fromL - i][fromC + i] == null) {
                    positions[i - 1] = new int[]{fromL - i, fromC + i};

                } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                    positions[i - 1] = new int[]{fromL - i, fromC + i};

                } else {
                    return false;
                }
            }
            return true;
        } else {
            int fromL = getMin(line, toLine);
            int fromC = getMin(column, toColumn);
            int toL = getMax(line, toLine);
            int toC = getMax(column, toColumn);

            int[][] positions = new int[toC - fromC][1];

            for (int i = 1; i < toC - fromC; i++) {
                if (chessBoard.board[fromL + i][fromC + i] == null) {
                    positions[i - 1] = new int[]{fromL + i, fromC + i};
                } else if (!chessBoard.board[fromL + i][fromC + i].color.equals(this.color) && fromL + i == toLine) {
                    positions[i - 1] = new int[]{fromL + i, fromC + i};
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean moveLikeRook(ChessBoard chessBoard, int line, int column, int toLine, int toColumn){
        if (column == toColumn) {

            for (int i = getMin(line, toLine); i < getMax(line, toLine); i++) {

                if (chessBoard.board[i][column] != null) {

                    if (chessBoard.board[i][column] == this && i == getMax(line, toLine)) return false;

                    else if (chessBoard.board[i][column].color.equals(this.color) && i == toLine) return false;

                    else if (!chessBoard.board[i][column].color.equals(this.color) && i == toLine) return true;
                        // Позволяет рубить другие фигуры и не переходить сквозь своих. НЕ ПОНИМАЮ!!!
                    else if (i != toLine && i != line) return false;
                }
            }
            if (chessBoard.board[toLine][column] != null) {

                if (chessBoard.board[toLine][column].color.equals(this.color) && chessBoard.board[toLine][column] != this)
                    return false;

                else return !chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this;
            } else return true;

        } else if (line == toLine) {
            for (int i = getMin(column, toColumn); i < getMax(column, toColumn); i++) {
                if (chessBoard.board[line][i] != null) {
                    if (chessBoard.board[line][i] == this && i == getMax(column, toColumn)) return false;
                    else if (chessBoard.board[line][i].color.equals(this.color) && i == toColumn) return false;
                    else if (!chessBoard.board[line][i].color.equals(this.color) && i == toColumn) return true;
                        // волшебная строчка...
                    else if (i != toLine && i != column) return false;
                }
            }
            if (chessBoard.board[toLine][toColumn] != null) {
                if (chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this)
                    return false;
                else
                    return !chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this;
            } else return true;
        } else return false;
    }



}
