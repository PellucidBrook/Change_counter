package DiffLib;

/**
 * Append a block of lines to the end of the old file.
 */
public class AppendCommand extends EditCommand
{
    public AppendCommand(FileInfo oldFileInfo, FileInfo newFileInfo)
    {
        super( oldFileInfo, newFileInfo );
        command = "Append";
        //newLines = newFileInfo.nextBlock();
        oldLines = oldFileInfo.getBlockAt( oldFileInfo.lineNum );
        newLines = newFileInfo.nextBlock();
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
