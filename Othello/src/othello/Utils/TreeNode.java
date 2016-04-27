/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello.Utils;

/**
 *
 * @author Lamia
 */




import java.util.ArrayList;
import java.util.List;


public class TreeNode implements java.io.Serializable
{
  

   
    /**
     * Children : the list of nodes just below in the tree
     */
    protected final List<TreeNode> children = new ArrayList<TreeNode>();

    /**
     * Container : the node just above in the tree
     */
    protected TreeNode parent;

    protected  Tablero tablero;

    protected int valor;  // el valor que devolvera el evaluador

    
    
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
   
    /**
     * Create a node in the tree, given its parent
     *
     *
     * @param parent the containing node, or null otherwise
     */


    public TreeNode (TreeNode parent)
    {
        
        if (parent != null) {
            parent.addChild(this);
        }
    }

  
    /**
     * Report the list of (direct) children
     *
     * @return the children
     */
    public List<TreeNode> getChildren ()
    {
       
        return children;
    }

   
    /**
     * Report the list of (direct) children
     *
     * @return the children
     */
    public synchronized List<TreeNode> getChildrenCopy ()
    {
       
        return new ArrayList(children);
    }

    //-------------------//
    // setChildrenParent //
    //-------------------//
    /**
     * Register this node as the parent of all its children
     */
    public void setChildrenParent ()
    {
        // Make all children point to this node as parent
        for (TreeNode node : children) {
            node.setParent(this);
            node.setChildrenParent(); // Recursively
        }
    }

   
    /**
     * Report the next node in the children of this node parent
     *
     *
     * @return the next sibling node, or null if none
     */
    public TreeNode getNextSibling ()
    {
        if (parent != null) {
            int index = parent.children.indexOf(this);

            if (index < (parent.children.size() - 1)) {
                return parent.children.get(index + 1);
            }
        }

        return null;
    }

    
    /**
     * Modify the link to the parent of this node
     *
     * @param parent the (new) parent
     */
    public void setParent (TreeNode parent)
    {
        
        this.parent = parent;
    }

    
    /**
     * Report the parent of this node
     *
     * @return the node just higher in the tree, or null if none
     */
    public TreeNode getParent ()
    {
        return parent;
    }

    
    /**
     * Report the previous node in the children of this node parent
     *
     *
     * @return the previous sibling node, or null if none
     */
    public TreeNode getPreviousSibling ()
    {
        if (parent != null) {
            int index = parent.children.indexOf(this);

            if (index > 0) {
                return parent.children.get(index - 1);
            }
        }

        return null;
    }

    
    /**
     * Add a child in the list of node children
     *
     * @param node the child to include
     */
    public synchronized void addChild (TreeNode node)
    {
      
        children.add(node);
        node.setParent(this);
    }

    
    /**
     * Utility to dump recursively all the children of this node, with no
     * starting indentation
     */
    public void dumpChildren ()
    {
        dumpChildren(0);
    }

    
    /**
     * Utility to dump recursively all the children of this node, with the
     * starting indentation specified
     *
     * @param level the starting indentation level
     */
    public void dumpChildren (final int level)
    {
        for (TreeNode node : children) {
            if (node.dumpNode(level)) {
                node.dumpChildren(level + 1);
            }
        }
    }

    /**
     * Utility to dump the current node, with no indentation
     *
     * @return true, so that processing continues
     */
    public boolean dumpNode ()
    {
        return dumpNode(0);
    }

    
    /**
     * Utility to dump the current node, with the specified level of
     * indentation.
     *
     * @param level the desired indentation level
     *
     * @return true, so that processing continues
     */
    public boolean dumpNode (int level)
    {
      
        return true; // Let computation continue down the tree
    }
}
