package com.koke.utils;

import com.koke.model.entity.common.Tree;
import com.koke.model.entity.common.TreeNode;
import com.koke.model.entity.common.TreeNodeConfig;

import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 树工具类
 */
@UtilityClass
public class TreeUtil {

    /**
     * 构建树形结构
     *
     * @param treeNodes 树形节点属性
     * @return
     */
    public List<Tree> build(List<TreeNode> treeNodes) {
        return build(treeNodes, TreeNodeConfig.DEFAULT_CONFIG);
    }

    /**
     * 构建树形结构
     *
     * @param treeNodes      树形节点属性
     * @param treeNodeConfig 树形节点配置
     * @return
     */
    public List<Tree> build(List<TreeNode> treeNodes, TreeNodeConfig treeNodeConfig) {
        Map<Long, List<Tree>> childrenListMap = new HashMap<>();
        Map<Long, Tree> nodeIds = new HashMap<>();
        for (TreeNode treeNode : treeNodes) {
            Long parentId = treeNode.getParentId();
            List<Tree> childrenTree = childrenListMap.get(parentId);
            if (childrenTree == null) {
                childrenTree = new ArrayList<>();
            }
            Tree tree = treeNodeToTree(treeNode, treeNodeConfig);
            childrenTree.add(tree);
            childrenListMap.put(parentId, childrenTree);
            nodeIds.put(treeNode.getId(), tree);
        }
        List<Tree> trees = new ArrayList<>();
        List<Long> parentIds = new ArrayList<>();
        for (TreeNode treeNode : treeNodes) {
            List<Tree> childrenTree = childrenListMap.get(treeNode.getParentId());
            Tree tree = nodeIds.get(treeNode.getParentId());
            if (tree != null) {
                tree.put(treeNodeConfig.getChildren(), childrenTree);
                if (nodeIds.get(tree.get(treeNodeConfig.getParentId())) == null) {
                    parentIds.add(treeNode.getParentId());
                    int frequency = Collections.frequency(parentIds, treeNode.getParentId());
                    if (frequency == 1) {
                        trees.add(tree);
                    }
                }
            } else {
                if (childrenListMap.get(treeNode.getId()) == null) {
                    trees.add(treeNodeToTree(treeNode, treeNodeConfig));
                }
            }
        }
        sort(trees, treeNodeConfig);
        return trees;
    }

    /**
     * TreeNode 转 Tree
     *
     * @param treeNode       树形节点属性
     * @param treeNodeConfig 树形节点配置
     * @return
     */
    private Tree treeNodeToTree(TreeNode treeNode, TreeNodeConfig treeNodeConfig) {
        Tree tree = new Tree();
        tree.put(treeNodeConfig.getId(), treeNode.getId());
        tree.put(treeNodeConfig.getName(), treeNode.getName());
        tree.put(treeNodeConfig.getParentId(), treeNode.getParentId());
        tree.put(treeNodeConfig.getSort(), treeNode.getSort());
        Map<String, Object> extra = treeNode.getExtra();
        if (!CollectionUtils.isEmpty(extra)) {
            for (Map.Entry<String, Object> entry : extra.entrySet()) {
                tree.put(entry.getKey(), entry.getValue());
            }
        }
        return tree;
    }

    /**
     * 对树形结构排序
     *
     * @param trees          树形结构
     * @param treeNodeConfig 树形节点配置
     */
    private void sort(List<Tree> trees, TreeNodeConfig treeNodeConfig) {
        Collections.sort(trees, (o1, o2) -> {
            Integer sort1 = (Integer) o1.get(treeNodeConfig.getSort());
            Integer sort2 = (Integer) o2.get(treeNodeConfig.getSort());
            if (sort1 != null && sort2 != null) {
                return sort1 - sort2;
            }
            return 0;
        });
        for (Tree tree : trees) {
            List<Tree> childrenTree = (List<Tree>) tree.get(treeNodeConfig.getChildren());
            if (childrenTree != null) {
                sort(childrenTree, treeNodeConfig);
            }
        }
    }

    /**
     * 设置树的属性
     * @return 树的配置文件
     */
    public TreeNodeConfig getTreeNodeConfig() {
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setId("unitId");
        treeNodeConfig.setName("unitName");
        return treeNodeConfig;
    }



}
