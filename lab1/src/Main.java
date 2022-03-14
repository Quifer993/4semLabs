public class Main {
    private static final int INPUT_FILE = 0;
    private static final int OUTPUT_FILE = 1;

    public static void main(String[] args){
        Read reading = new Read();
        String inputFileName = args[INPUT_FILE];//new
//	    String inputFileName = "in.txt";
        CodeError isErrorOpenFile = reading.readingFile(inputFileName);
        if (isErrorOpenFile == CodeError.FILE_NOT_OPEN) {
            System.out.println("File don`t exist");
            return;
        }

        Parser dictionary = new Parser(reading.getMap());
        dictionary.parser();

        Write writing = new Write( dictionary.getMap(),
                reading.getCapacityWords(),
                reading.getCapacityDifferentWords() );

        String outputFileName = args[OUTPUT_FILE];
//	std::string outputFileName = "output.csv";
        isErrorOpenFile = writing.writeCSV(outputFileName);
        if (isErrorOpenFile == CodeError.FILE_NOT_OPEN) {
            System.out.println("File don`t exist");
        }

    }

}

