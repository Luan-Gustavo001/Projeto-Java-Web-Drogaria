package br.com.luan.drogaria.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SobreBean implements Serializable {
	
	private TreeNode root;

	@PostConstruct
	public void init() {
		root = new DefaultTreeNode("Nossa História", null);
		TreeNode node0 = new DefaultTreeNode("2015", root);
		TreeNode node1 = new DefaultTreeNode("2016", root);
		TreeNode node2 = new DefaultTreeNode("2017", root);

		node1.getChildren().add(new DefaultTreeNode("#######"));
		node0.getChildren().add(new DefaultTreeNode("#######"));
		node2.getChildren().add(new DefaultTreeNode("#######"));

	}

	public TreeNode getRoot() {
		return root;
	}


		
}