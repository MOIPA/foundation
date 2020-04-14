package com.ds.graph;

/**
 * @author TR
 * @content 图的定义，无向图，使用邻接矩阵存储
 */
public class GraphMatrix {
    // 顶点列表
    Vertex vertexList[] = null;
    // 邻接矩阵 如果是有向图要把int改为float
    int edges[][] = null;
    // 顶点数和边数
    int n=0,e=0;

    public GraphMatrix(int size) {
        vertexList = new Vertex[size];
        edges = new int[size][size];
    }
}
