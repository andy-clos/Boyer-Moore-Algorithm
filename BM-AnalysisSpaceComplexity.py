"""
ASSIGNMENT 2 (Analysis Space Complexity for Boyer-Moore Algorithm)

MEMBER 1: ANDYCLOS A/L BOON MEE (22300738)
MEMBER 2: MUHAMMAD EZZAT HAZIQ BIN ELHAN (22300604)
MEMBER 3: KAM WENG XUAN (22300683)
MEMBER 4: LOH WEI TING (22300549)
"""

import pandas as pd
import matplotlib.pyplot as plt

# manually input data based on the run experiment
data = [
    {"pattern_size": 10, "alphabet_size": 26, "total_space": 200},
    {"pattern_size": 20, "alphabet_size": 26, "total_space": 240},
    {"pattern_size": 30, "alphabet_size": 26, "total_space": 280},
    {"pattern_size": 40, "alphabet_size": 26, "total_space": 320},
    {"pattern_size": 50, "alphabet_size": 26, "total_space": 360},
]

# Convert the list of dictionaries to a DataFrame
df = pd.DataFrame(data)

# Plot the data
plt.figure(figsize=(10, 6))

# Create separate lines for each alphabet size
for alphabet_size in df['alphabet_size'].unique():
    subset = df[df['alphabet_size'] == alphabet_size]
    plt.plot(subset['pattern_size'], subset['total_space'], label=f'Alphabet size: {alphabet_size}')

plt.xlabel('Pattern Size')
plt.ylabel('Total Space (bytes)')
plt.title('Boyer-Moore Algorithm Space Complexity - O(k+m)')
plt.legend()
plt.grid(True)
plt.show()