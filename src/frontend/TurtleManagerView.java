package frontend;


public class TurtleManagerView extends ModuleView{
    private static final double WIDTH = 250;
    private static final double HEIGHT = 150;
    
    public TurtleManagerView (EntryManager manager, String labelTitle, String[] colTitles) {
        super(manager, labelTitle, colTitles);
    }
//
//    private void setRowResponse () {
//        getMyTableView().setRowFactory(tv->{TableRow<Entry> row = new TableRow<>();
//        row.setOnMouseClicked(e->{if(e.getClickCount()==2&&!row.isEmpty()){
//            Alert err = new Alert(AlertType.INFORMATION);
//            err.show();
//        }
//        });});
//        
//        }

    

    private void openOptions () {
       
    }

    @Override
    protected void setSizing () {
        getMyTableView().setPrefSize(WIDTH, HEIGHT);
        
    }

}
