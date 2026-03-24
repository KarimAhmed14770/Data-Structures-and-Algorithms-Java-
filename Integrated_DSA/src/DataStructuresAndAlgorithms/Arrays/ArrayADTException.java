package DataStructuresAndAlgorithms.Arrays;

public class ArrayADTException extends RuntimeException {//RuntimeException to make it unchecked exception

    public ArrayADTException(String message) {
        super(message);//this is the best way so that JVM also understands
    }
}