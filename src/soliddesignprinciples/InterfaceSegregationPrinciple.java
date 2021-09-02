package soliddesignprinciples;

class Document {

}

interface Machine
{
    void print(Document d);
    void fax(Document d) throws Exception;
    void scan(Document d);
}

class MultiFunctionPrinter implements Machine
{

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

class OldFashionedPrinter implements Machine
{

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) throws Exception {
        throw new Exception();
    }

    @Override
    public void scan(Document d) {

    }
}

interface Printer
{
    void print(Document document);
}

interface Scanner
{
    void scan(Document document);
}

class JustAPrinter implements Printer
{

    @Override
    public void print(Document document) {

    }
}

class Photocopier implements Printer, Scanner
{

    @Override
    public void print(Document document) {

    }

    @Override
    public void scan(Document document) {

    }
}

interface MultiFunctionDevice extends Printer, Scanner {}

class MultiFunctionMachine implements MultiFunctionDevice{

    private Printer printer;
    private Scanner scanner;

    public MultiFunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document document) {
        printer.print(document);
    }

    @Override
    public void scan(Document document) {
        scanner.scan(document);
    }
}

class InterfaceSegregationPrincipleTest {
}
