package com.group7.sys.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

  /**
   * 把没有层级关系的集合变得有层级关系
   *
   * @param treeNodes
   * @param topID
   * @return
   */
  public static List<TreeNode> build(List<TreeNode> treeNodes, Integer topID) {
    List<TreeNode> nodes = new ArrayList<>();

    for (TreeNode node : treeNodes) {
      if (node.getPid().equals(topID)) {
        nodes.add(node);
      }
      for (TreeNode child : treeNodes) {
        if (node.getId().equals(child.getPid())) {
          node.getChildren().add(child);
        }
      }
    }
    return nodes;
  }
}
