public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Слон при перемещении всегда меняет и столбец и линию, т.к. ходит по диагонали
        if (line != toLine && column != toColumn &&
                // и идет по диагонали (насколько вправо/влево перешли - настолько же и вверх/вниз)
                Math.abs(line - toLine) == Math.abs(column - toColumn) &&
                // и не выходим за рамки доски
                checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) &&
                // куда идем, там либо пусто, либо фигура другого цвета
                (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                // и на клетке с которой мы ходим, есть фигура
                chessBoard.board[line][column] != null) {
            // решение 8 задачи...
            if (!chessBoard.board[line][column].equals(this)) return false;


            // Проверка, что на пути не будут стоять другие фигуры
            /* слон ходит слева направо сверху вниз и обратно или же справа налево снизу вверх и обратно...
            * Рассмотрим эти два варианта, в первом случае (слева направо сверху вниз) после перемещения
            *  наша колонка изменится в большую сторону, а линия уменьшится настолько же
            *
            * получается так: если column < toColumn и line > toLine */
            if ((column == getMin(column, toColumn) && line == getMax(line, toLine)) ||
                    // или обратно, колонка стала меньше, а линия больше: toColumn > column и toLine < line
                    (toColumn == getMin(column, toColumn) && toLine == getMax(line, toLine))) {
                // создадим четыре переменные, для описания движения слона слева направо сверху вниз
                int fromL = getMax(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMin(line, toLine);
                int toC = getMax(column, toColumn);
                // позиции по которым слон проходит по пути
                int[][] positions = new int[toC - fromC][1];
                // число колонок = числу линий по которым проходит слон
                for (int i = 1; i < toC - fromC; i++) { // проходим по колонкам
                    // i будет идти по колонкам от (i = 1) до (toC - fromC) и каждый раз проверять:
                    // пустая ли там клетка?
                    if (chessBoard.board[fromL - i][fromC + i] == null) {
                        // если да, то записываем ("i - 1" - это первый элемент массива - [0])
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                        // иначе если там не пусто, но на пути фигура противоположного цвета, и это клетка назначения,
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                        // то тоже пойдет - записываем
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else {
                        //иначе на пути до пункта назначения стоит препятствие
                        return false;
                    }
                }
                return true;
                // Теперь все то же, только в направлении справа налево снизу вверх и обратно, т.е.
                //колонка увеличивается на столько же, насколько и линия
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
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
