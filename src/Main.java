

public class Main {
    public static void main(String[] args){
        Read reading;
        String inputFileName = args[INPUT_FILE];//new
        std::string inputFileName = argv[INPUT_FILE];
//	std::string inputFileName = "in.txt";
        CodeError isErrorOpenFile = reading.readingFile(inputFileName);
        if (isErrorOpenFile == CodeError::FILE_NOT_OPEN) {
            std::cout << "File don`t exist" << std::endl;
            return 1;
        }

        Parcer dictionary(reading.Read::getMap());
        dictionary.Parcer::parcer();

        Write writing(	dictionary.Parcer::getList(),
                reading.Read::getCapacityWords(),
                reading.Read::getCapacityDifferentWords()
        );

        std::string outputFileName = argv[OUTPUT_FILE];
//	std::string outputFileName = "output.csv";
        isErrorOpenFile = writing.Write::writeCSV(outputFileName);
        if (isErrorOpenFile == CodeError::FILE_NOT_OPEN) {
            std::cout << "File don`t exist" << std::endl;
            return 1;
        }

        return 0;
    }

    }

}
