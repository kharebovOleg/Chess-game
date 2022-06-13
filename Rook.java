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

            return moveLikeRook(chessBoard, line, column, toLine, toColumn);
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
