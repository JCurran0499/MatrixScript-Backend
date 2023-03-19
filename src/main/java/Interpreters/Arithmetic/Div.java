package Interpreters.Arithmetic;

import Interpreters.Interpreter;
import Interpreters.Primitive;
import Interpreters.Primitives.*;

public class Div implements Interpreter {
    private final Interpreter i1;
    private final Interpreter i2;

    public Div(Interpreter i1, Interpreter i2) {
        this.i1 = i1;
        this.i2 = i2;
    }

    /* Base Methods */

    public Primitive solve() {
        Primitive p1 = i1.solve();
        Primitive p2 = i2.solve();

        // --------- Errors --------- \\

        if (p1.id().equals("err"))
            return p1;
        if (p2.id().equals("err"))
            return p2;

        if (p1.id().equals("null") || p2.id().equals("null"))
            return new Err("imbalanced division");

        // --------- Computation --------- \\

        if (p1.id().equals("num")) {
            return ((Num) p1).divide(p2).solve();
        }

        if (p1.id().equals("mat")) {
            return ((Mat) p1).divide(p2).solve();
        }

        return new Err("invalid division");
    }

    public String id() {
        return "div";
    }

}