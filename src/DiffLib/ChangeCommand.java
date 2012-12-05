package DiffLib;

/**
 * Change a block in the old file to a different block in the new file.
 */
public class ChangeCommand extends EditCommand
{
	
	
    public ChangeCommand(FileInfo oldFileInfo, FileInfo newFileInfo)
    {
        super( oldFileInfo, newFileInfo );
        command = "Change";
        oldLines = oldFileInfo.nextBlock();
        newLines = newFileInfo.nextBlock();
        oldLines.reportable = true;
        newLines.reportable = true;
    }
    
    public String getCommand() {
    	return command;
    }
    
    public LineBlock getOld() {
    	return oldLines;
    }
    
    public LineBlock getNew() {
    	return newLines;
    }
    
}
