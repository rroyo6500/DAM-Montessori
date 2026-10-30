using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.PlayerLoop;
using UnityEngine.UIElements;

public class PlayerScript : MonoBehaviour
{

    public float horizontalSpeed = 10f;
    public float jumpForce = 300f;
    public bool jumping = false;

    public Vector2 boxSize;
    public float boxDistance;

    public LayerMask floorMask;
    
    private Vector3 _movement;
    
    private Rigidbody2D _body;
    private Animator _animator;
    private SpriteRenderer _spriteRenderer;
    
    private Vector3 _horizontalVelocity;
    private Vector3 _verticalVelocity;
    
    // Start is called before the first frame update
    void Start()
    {
        _body = GetComponent<Rigidbody2D>();
        _animator = GetComponent<Animator>();
        _spriteRenderer = GetComponent<SpriteRenderer>();
        
        _movement = Vector3.zero;
    }

    // Update is called once per frame
    void Update()
    {
        Movement();
        Jump();
        RotatePj();
        IsInTheFloor();
        UpdateAnimation();
    }

    private void FixedUpdate()
    {

        _body.velocity = new Vector2(
            _movement.x * horizontalSpeed,
            _body.velocity.y
        );

        if (jumping)
        {
            _body.AddForce(Vector2.up * jumpForce);
            jumping = false;
        }
        
    }
    
    private bool IsInTheFloor()
    {
        RaycastHit2D rc = Physics2D.BoxCast(
            transform.position, new Vector2(0.5f, .1f), 0, Vector2.down, boxDistance, floorMask);
        return true;
    }

    private void UpdateAnimation()
    {
        
        if (_movement.x == 0)
        {
            _animator.SetBool("Moviendo", false);
        }
        else
        {
            _animator.SetBool("Moviendo", true);
        }
        
    }

    private void RotatePj()
    {
        if (_body.velocity.x > 0)
        {
            _spriteRenderer.flipX = false;
        }
        else if (_body.velocity.x < 0)
        {
            _spriteRenderer.flipX = true;
        }
    }

    private void Jump()
    {
        if (Input.GetKeyDown(KeyCode.Space) && !jumping && IsInTheFloor())
        {
            jumping = true;
        }
    }

    private void Movement()
    {
        _movement = Vector3.zero;
        if (Input.GetKey(KeyCode.D))
        {
            _movement = Vector3.right;
        }

        if (Input.GetKey(KeyCode.A))
        {
            _movement = Vector3.left;
        }
    }
    
    void OnDrawGizmos()
    {
        Gizmos.DrawWireCube(transform.position + Vector3.down * boxDistance, boxSize);
    }
}
