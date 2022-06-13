public class Queen extends ChessPiece{
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line != toLine && column != toColumn &&
                Math.abs(line - toLine) == Math.abs(column - toColumn) &&
                checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) &&
                (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                chessBoard.board[line][column] != null) {

            return moveLikeBishop(chessBoard, line, column, toLine, toColumn);

        } else if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {

            return moveLikeRook(chessBoard, line, column, toLine, toColumn);

        } else return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
