
def edge_pairs_to_adjacency_list_directed(edge_pairs):
    adjacency_list = {}

    for edge in edge_pairs:
        source, destination = edge

        # Add destination to the adjacency list of source
        if source in adjacency_list:
            adjacency_list[source].append(destination)
        else:
            adjacency_list[source] = [destination]

        # If the destination vertex is not in the adjacency list yet, add it with an empty list
        if destination not in adjacency_list:
            adjacency_list[destination] = []

    return adjacency_list

# Example usage


def convert_input_to_edge_pairs(input_str):
    edge_pairs = []
    for line in input_str.split('\n'):
        if line.strip():  # Check if line is not empty
            edge = tuple(map(int, line.split()))
            edge_pairs.append(edge)
    return edge_pairs

input_str = """1 2
2 3
3 4
3 5
3 7
4 6
4 5
5 6
5 7
6 7
7 8
7 9
8 10
8 9
9 10
10 11
10 12
11 12
12 13
13 14
13 15
14 15"""

edge_pairs = convert_input_to_edge_pairs(input_str)
print("Edge pairs:", edge_pairs)



def topological_sort(graph):
    # Set to keep track of visited vertices
    visited = set()
    # List to store the sorted vertices
    result = []

    def dfs(vertex):
        visited.add(vertex)
        for neighbor in graph.get(vertex, []):
            if neighbor not in visited:
                dfs(neighbor)
        result.append(vertex)

    # Visit every vertex in the graph
    for vertex in graph.keys():
        if vertex not in visited:
            dfs(vertex)

    # Reverse the result to get the topological order
    result.reverse()
    return result

# Example usage
# edge_pairs = [(1, 2), (2, 3), (1, 3), (3, 4)]
# adjacency_list = edge_pairs_to_adjacency_list_directed(edge_pairs)
# print(adjacency_list)
# topological_order = topological_sort(adjacency_list)
# print("Topological order:", topological_order)


def calculate(adjacency_list, src, dst, topological_order, vertices):
    arr = [0] * (vertices + 1)
    arr[dst] = 1

    topological_order.reverse()

    for i in topological_order[1:]:
        for neighbour in adjacency_list[i]:
            arr[i] += (arr[neighbour])

    topological_order.reverse()
    print(arr)
    return arr

def inverse_adjacency_list(adjacency_list):
    inverse_adj_list = {}

    for vertex, neighbors in adjacency_list.items():
        for neighbor in neighbors:
            if neighbor in inverse_adj_list:
                inverse_adj_list[neighbor].append(vertex)
            else:
                inverse_adj_list[neighbor] = [vertex]

    return inverse_adj_list



# edge_pairs = [(1, 2), (2, 3), (1, 3), (3, 4)]
adjacency_list = edge_pairs_to_adjacency_list_directed(edge_pairs)
print(adjacency_list)
# print(len(adjacency_list))
topological_order = topological_sort(adjacency_list)
arr = calculate(adjacency_list, 2, 13, topological_order, 15)
inverse = inverse_adjacency_list(adjacency_list)
topological_order_inverse = topological_sort(inverse)
arr_inverse = calculate(inverse, 13, 2, topological_order_inverse, 15)
src = 2
dst = 13
target = arr[2] * arr_inverse[2]
for i in range(src + 1, dst):
    if(arr[i] * arr_inverse[i] == target):
        print(i)
        
