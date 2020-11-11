class MissingFieldException extends Exception {
    public MissingFieldException() { }
    public MissingFieldException( String msg ) {
        super( "Name not Found" );
    }

}