public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // не выходим за рамки доски
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            // ладья ходит либо по вертикали (column = toColumn), либо по горизонтали (line == toLine)
            // если по вертикали
            if (column == toColumn) {
                // проходим линии сверху вниз, хотя можно и снизу вверх
                for (int i = getMin(line, toLine); i < getMax(line, toLine); i++) {
                    // если следующая клетка не занята
                    if (chessBoard.board[i][column] != null) {
                        // нельзя двигаться туда, где стоишь
                        if (chessBoard.board[i][column] == this && i == getMax(line, toLine)) return false;
                        // нельзя рубить фигуру своего же цвета
                        else if (chessBoard.board[i][column].color.equals(this.color) && i == toLine) return false;
                        // после выполнения всех вышеуказанных условий возвращаем true
                        else if (!chessBoard.board[i][column].color.equals(this.color) && i == toLine) return true;
                        // Позволяет рубить другие фигуры и не переходить сквозь своих. НЕ ПОНИМАЮ!!!
                        else if (i != toLine && i != line) return false;
                    }
                } // если на конечной клетке стоит фигура
                if (chessBoard.board[toLine][column] != null) {
                    // нельзя рубить фигуру своего же цвета
                    if (chessBoard.board[toLine][column].color.equals(this.color) && chessBoard.board[toLine][column] != this)
                        return false;
                    // вернет true если на конечной точке стоит фигура другого цвета и конечная точка не совпадает с начальной
                    else return !chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this;
                } else return true;
                // а теперь если ладья ходит вдоль линии ()
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
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
