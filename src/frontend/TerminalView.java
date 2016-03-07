package frontend;

public class TerminalView extends ModuleView {
    private static final double WIDTH = 250;
    private static final double HEIGHT = 230;
    private static final double COMMAND_WIDTH = 170;
    private int commandColIndex = 0;
    
    
    public TerminalView(CommandLine command, EntryManager manager, String labelTitle, String[] columnTitles){
        super(manager, labelTitle, columnTitles);
        defineListener(command);
    }

    @Override
    protected void setSizing () {
        getMyTableView().setPrefSize(WIDTH,HEIGHT);
        getMyTableView().getColumns().get(commandColIndex).setPrefWidth(COMMAND_WIDTH);
    }


}
