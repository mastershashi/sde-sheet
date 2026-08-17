For Google, Meta, Microsoft, Uber, Databricks, Stripe, Adobe, Cisco, you do not memorize 30 linked list problems. You memorize 5-6 pointer patterns. Every other problem is just a variation.

This is exactly how I prepare candidates.

The Only 6 Patterns You Need
Pattern Appears In FAANG Frequency

1. Dummy Node ⭐⭐⭐⭐⭐ Very High
2. Fast & Slow Pointer ⭐⭐⭐⭐⭐ Very High
3. Pointer Reversal ⭐⭐⭐⭐⭐ Extremely High
4. Pointer Stitching (Merge/Rewire) ⭐⭐⭐⭐⭐ Extremely High
5. K-Group / Window ⭐⭐⭐⭐ High
6. HashMap + Doubly Linked List ⭐⭐⭐⭐ High (Design)

Almost every interview question is built from these.

Pattern 1 — Dummy Node
Recognition

Whenever you see

delete
insert
merge
partition
remove

Think

"I'll create a dummy node."

Generic Skeleton
ListNode dummy = new ListNode(-1);
dummy.next = head;

ListNode prev = dummy;
ListNode curr = head;

while (curr != null) {

    // manipulate

    prev = curr;
    curr = curr.next;

}

return dummy.next;
Problems

Easy

Remove Elements
Merge Two Lists

Medium

Remove Nth Node
Swap Nodes
Partition List
FAANG Twist

Instead of

Remove node

They ask

Remove every kth node.

Nothing changes.

Just change

if(...)

condition.

Pattern 1: Dummy Node

Recognition

Delete
Insert
Merge
Partition
LC	Problem	Companies	Twist
21	Merge Two Sorted Lists	Amazon, Microsoft, Adobe	Basic pointer stitching
19	Remove Nth Node From End	Meta, Google, Amazon	Dummy + Fast/Slow
203	Remove Linked List Elements	Amazon	Multiple deletions
86	Partition List	Google, Uber	Build two lists and join
82	Remove Duplicates from Sorted List II	Meta	Delete all duplicate values

FAANG Twist

Delete every node satisfying a custom condition instead of a fixed value.

Pattern 2 — Fast & Slow Pointer

Recognition

Whenever question says

middle
cycle
nth from end
palindrome

Immediately

slow=head;
fast=head;
Skeleton
while(fast!=null && fast.next!=null){

    slow=slow.next;

    fast=fast.next.next;

}
Problems

Middle

Cycle

Cycle Start

Palindrome

Remove nth

Happy Number

FAANG Twist

Instead of

Find middle

They ask

Split list.

Nothing changes.

Only

mid.next=null;

Another twist

Instead of

Palindrome

They ask

Restore original list after checking.

Need

Reverse twice.

Pattern 3 — Reverse Pointer

Recognition

Whenever you see

Reverse

Rotate

Reorder

Palindrome

Skeleton

This is THE most important snippet.

prev=null;

curr=head;

while(curr!=null){

    next=curr.next;

    curr.next=prev;

    prev=curr;

    curr=next;

}

return prev;

Memorize forever.

Problems

Reverse

Reverse Between

Palindrome

Reverse K

Reorder

Rotate

FAANG Twist

Instead of

Reverse list

They ask

Reverse only

even values

or

every alternate group

or

reverse after middle

Reverse logic

Never changes.

Only

Finding boundaries changes.

Pattern 4 — Pointer Stitching

Most people don't realize this is a pattern.

Recognition

Whenever problem says

Merge

Reorder

Swap

Zip

Interleave

Generic Idea

Never lose next node.

next = curr.next;

curr.next = something;

curr = next;
Example

Merge

A

↓

B

↓

A

↓

B

Reorder

1 2 3 4

↓

1 4 2 3

Swap

1 2

↓

2 1

All use

Pointer stitching.

FAANG Twist

They combine

Reverse

Merge

Example

Reorder List

Find Middle

↓

Reverse

↓

Merge
Pattern 5 — K Window

Recognition

Question contains

K

Window

Group

Chunk

Batch

Generic Steps
Find kth node

↓

Detach group

↓

Reverse

↓

Reconnect

Always.

Skeleton

beforeGroup

groupStart

groupEnd

nextGroup

Problems

Reverse K Group

Reverse Alternate K

Rotate

FAANG Twist

Instead of

Reverse every K

They ask

Reverse

Only if

sum of group is even.

Algorithm

Never changes.

Only condition changes.

Pattern 6 — HashMap + DLL

Recognition

Question says

Cache

History

Undo

Recent

Browser

Need

HashMap

-

Double Linked List

Immediately.

Problems

LRU Cache

LFU Cache

Browser History

Text Editor

Music Queue

FAANG Twist

Design

Browser

Undo

Navigation

Almost always

DLL.

The FAANG Combination Pattern

This is where interviews become Medium/Hard.

They never invent new algorithms.

They combine patterns.

Combination 1
Middle

↓

Reverse

↓

Merge

Question

Reorder List

Combination 2
Dummy

↓

Fast Slow

↓

Delete

Question

Remove Nth Node

Combination 3
Reverse

↓

Compare

↓

Reverse Again

Question

Palindrome

Combination 4
Find K

↓

Reverse

↓

Reconnect

Question

Reverse K Group

Combination 5
Middle

↓

Recursive Build

Question

Sorted List to BST

FAANG Recognition Cheat Sheet
If Question Says... Think Immediately
Remove, Insert, Delete Dummy Node
Middle, Cycle, Nth from End Fast & Slow
Reverse, Rotate, Reorder Reverse Pattern
Merge, Zip, Swap Pointer Stitching
Every K, Group, Batch K-Window
Cache, Browser, History HashMap + DLL
Generic Interview Flow

When you read a linked-list question, ask yourself these questions in order:

1. Am I deleting or inserting?
   ↓
   Dummy

2. Do I need the middle or end?
   ↓
   Fast & Slow

3. Am I changing direction?
   ↓
   Reverse

4. Am I connecting two lists or nodes?
   ↓
   Pointer Stitching

5. Is there a K involved?
   ↓
   K Window

6. Is this a cache or history problem?
   ↓
   HashMap + DLL
   The 10 Problems That Cover Almost Everything

If you solve these deeply (not just memorize code), you'll be able to derive most linked-list questions asked by FAANG-level companies:

Reverse Linked List → Reverse Pattern
Middle of Linked List → Fast & Slow
Remove Nth Node From End → Dummy + Fast & Slow
Linked List Cycle II → Fast & Slow + Proof
Merge Two Sorted Lists → Dummy + Stitching
Reorder List → Middle + Reverse + Stitching
Reverse Linked List II → Reverse Within Boundaries
Reverse Nodes in K-Group → K-Window + Reverse
Copy List with Random Pointer → Pointer Manipulation + HashMap/O(1) trick
LRU Cache → HashMap + Doubly Linked List

This set gives you coverage of nearly every linked-list interview because the remaining questions are usually just new constraints on one of these six core patterns, not fundamentally new algorithms.
