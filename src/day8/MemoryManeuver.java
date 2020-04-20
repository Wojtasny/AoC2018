package day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryManeuver {
    List<Integer> inputLicense;
    List<Node> nodesList = new ArrayList<>();
    private static int index = 0;
    private Node root;

    public MemoryManeuver(String licenseFile) {
        this.inputLicense = Arrays.stream(licenseFile.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    void parseTree(){
        index = 0;
        root = new Node(index, ++index);
        root.parseNodes(root.numberOfNodes);
        nodesList.add(root);
    }

    public int getMetadataSum() {
        return nodesList.stream().mapToInt(Node::getMetadataSum).sum();
    }

    int getRootValue(){
        if(root == null){
            parseTree();
        }
        return root.getValue();
    }

    class Node {
        int numberOfNodes;
        int metadataEntriesQuantity;
        private List<Node> children = new ArrayList<>();
        private List<Integer> metadata = new ArrayList<>();

        public Node(int childNodesQuantityIndex, int metadataEntriesQuantityIndex) {
            this.numberOfNodes = inputLicense.get(childNodesQuantityIndex);
            this.metadataEntriesQuantity = inputLicense.get(metadataEntriesQuantityIndex);
        }

        int getMetadataSum(){
            return metadata.stream().mapToInt(Integer::intValue).sum();
        }

        int getValue(){
            if(numberOfNodes == 0){
                return this.getMetadataSum();
            } else {
                int sumOfKids = 0;
                for (Integer i :
                        metadata) {
                    if(i<=children.size() && i != 0){
                        sumOfKids += children.get(i-1).getValue();
                    }
                }
                return sumOfKids;
            }
        }
        void parseNodes(int amountOfNodes){
            for (int i=0; i<amountOfNodes; i++){

                Node childNode = new Node(++index, ++index);
                childNode.parseNodes(childNode.numberOfNodes);
                this.children.add(childNode);
                nodesList.add(childNode);
            }
            for(int m=1;m<=metadataEntriesQuantity; m++){
                metadata.add(inputLicense.get(++index));
            }
        }
    }
}
