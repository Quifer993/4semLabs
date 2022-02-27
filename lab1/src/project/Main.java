package project;

public class Main {
    private static final int INPUT_FILE = 1;
    private static final int OUTPUT_FILE = 2;

    public static int main(String[] args){
        Read reading;
        String inputFileName = args[INPUT_FILE];//new
//	    String inputFileName = "in.txt";
        CodeError isErrorOpenFile = reading.readingFile(inputFileName);
        if (isErrorOpenFile == CodeError.FILE_NOT_OPEN) {
            System.out.println("File don`t exist");
            return 1;
        }

        Parser dictionary(reading.getMap());
        dictionary.parser();

        Write writing( dictionary.getMap(),
                reading.getCapacityWords(),
                reading.getCapacityDifferentWords() );

        String outputFileName = args[OUTPUT_FILE];
//	std::string outputFileName = "output.csv";
        isErrorOpenFile = writing.writeCSV(outputFileName);
        if (isErrorOpenFile == CodeError.FILE_NOT_OPEN) {
            System.out.println("File don`t exist");
            return 1;
        }

        return 0;
    }

}

