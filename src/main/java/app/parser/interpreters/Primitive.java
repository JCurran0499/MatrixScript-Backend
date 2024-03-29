package app.parser.interpreters;

public abstract class Primitive implements Interpreter {

    public Primitive solve() {
        return this;
    }

    public abstract String id();
    
    public abstract String string();

    public abstract boolean equals(Primitive p);
}
