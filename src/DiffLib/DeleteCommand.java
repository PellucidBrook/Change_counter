package DiffLib;

/**
 * Delete a block from the old file.
 */
public class DeleteCommand extends EditCommand
{
    public DeleteCommand(FileInfo oldFileInfo, FileInfo newFileInfo)
    {
        super( oldFileInfo, newFileInfo );
        command = "Delete";
        oldLines = oldFileInfo.nextBlock();
        oldLines.reportable = true;
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
